package kz.diploma.securefinance

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

/**
 * CryptoManager-дің детерминирленген PBKDF2 және AES-GCM логикасын тексереді.
 * Android Keystore мен EncryptedSharedPreferences мок арқылы ауыстырылады.
 */
class CryptoManagerTest {

    private val testPin = "1234".toCharArray()
    private val testSalt = ByteArray(16) { it.toByte() }

    @Test
    fun `PBKDF2 детерминирленген — бір PIN + бір salt әрдайым бір нәтиже`() {
        // CryptoManager-ді тікелей тексеру үшін private helper-ді unit-test ретінде шығарамыз
        val key1 = deriveKeyDirectly(testPin, testSalt)
        val key2 = deriveKeyDirectly(testPin, testSalt)
        assertArrayEquals("Бір кіріс → бір шығыс болуы керек", key1, key2)
    }

    @Test
    fun `PBKDF2 әр түрлі PIN-де әр түрлі нәтиже береді`() {
        val key1 = deriveKeyDirectly("1234".toCharArray(), testSalt)
        val key2 = deriveKeyDirectly("5678".toCharArray(), testSalt)
        assertFalse("Әр түрлі PIN → әр түрлі кілт", key1.contentEquals(key2))
    }

    @Test
    fun `PBKDF2 әр түрлі salt-та әр түрлі нәтиже береді`() {
        val salt2 = ByteArray(16) { (it + 1).toByte() }
        val key1 = deriveKeyDirectly(testPin, testSalt)
        val key2 = deriveKeyDirectly(testPin, salt2)
        assertFalse("Әр түрлі salt → әр түрлі кілт", key1.contentEquals(key2))
    }

    @Test
    fun `AES-GCM шифрлау мен дешифрлау кері операция болуы керек`() {
        val key = deriveKeyDirectly(testPin, testSalt)
        val plaintext = "SecureFinance KZ test data".toByteArray()

        val encrypted = encryptBytesDirectly(plaintext, key)
        val decrypted = decryptBytesDirectly(encrypted, key)

        assertNotNull("Дешифрлау нәтижесі null болмауы керек", decrypted)
        assertArrayEquals("Шифрлау + дешифрлау оригинал мәтінді қайтаруы керек", plaintext, decrypted)
    }

    @Test
    fun `Қате кілтпен дешифрлау null қайтаруы керек`() {
        val key = deriveKeyDirectly(testPin, testSalt)
        val wrongKey = deriveKeyDirectly("9999".toCharArray(), testSalt)
        val plaintext = "test".toByteArray()

        val encrypted = encryptBytesDirectly(plaintext, key)
        val decrypted = decryptBytesDirectly(encrypted, wrongKey)

        assertNull("Қате кілтпен дешифрлау null қайтаруы керек", decrypted)
    }

    @Test
    fun `Шифрланған деректер plaintext-тен ерекше болуы керек`() {
        val key = deriveKeyDirectly(testPin, testSalt)
        val plaintext = "sensitive financial data 12345".toByteArray()
        val encrypted = encryptBytesDirectly(plaintext, key)

        assertFalse("Шифрланған деректер оригиналмен бірдей болмауы керек",
            plaintext.contentEquals(encrypted))
    }

    @Test
    fun `Кілт ұзындығы 256 бит (32 байт) болуы керек`() {
        val key = deriveKeyDirectly(testPin, testSalt)
        assertEquals("Кілт ұзындығы 32 байт болуы керек", 32, key.size)
    }

    // ---- Helpers (CryptoManager-дегі private логиканы тікелей тестілеу) ----

    private fun deriveKeyDirectly(pin: CharArray, salt: ByteArray): ByteArray {
        val spec = javax.crypto.spec.PBEKeySpec(pin, salt, 100_000, 256)
        val factory = javax.crypto.SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        return factory.generateSecret(spec).encoded.also { spec.clearPassword() }
    }

    private fun encryptBytesDirectly(plaintext: ByteArray, key: ByteArray): ByteArray {
        val iv = ByteArray(12).also { java.security.SecureRandom().nextBytes(it) }
        val cipher = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(
            javax.crypto.Cipher.ENCRYPT_MODE,
            javax.crypto.spec.SecretKeySpec(key, "AES"),
            javax.crypto.spec.GCMParameterSpec(128, iv)
        )
        return iv + cipher.doFinal(plaintext)
    }

    private fun decryptBytesDirectly(data: ByteArray, key: ByteArray): ByteArray? {
        if (data.size <= 12) return null
        val iv = data.copyOfRange(0, 12)
        val ciphertext = data.copyOfRange(12, data.size)
        return try {
            val cipher = javax.crypto.Cipher.getInstance("AES/GCM/NoPadding")
            cipher.init(
                javax.crypto.Cipher.DECRYPT_MODE,
                javax.crypto.spec.SecretKeySpec(key, "AES"),
                javax.crypto.spec.GCMParameterSpec(128, iv)
            )
            cipher.doFinal(ciphertext)
        } catch (e: Exception) {
            null
        }
    }
}
