package kz.diploma.securefinance.security

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * ТЗ 8.2: PIN → PBKDF2 → K_user → AES-GCM → Enc(K_db).
 * Деректер қарапайым SharedPreferences-та сақталады (AES-GCM шифрмен қорғалған).
 * EncryptedSharedPreferences алынып тасталды — кейбір құрылғыларда Keystore init crash береді.
 */
class CryptoManager(context: Context) {

    companion object {
        private const val PBKDF2_ITERATIONS = 100_000
        private const val KEY_BITS = 256
        private const val SALT_LEN = 16
        private const val IV_LEN = 12
        private const val GCM_TAG = 128
        private const val AES_GCM = "AES/GCM/NoPadding"
        private const val PBKDF2_ALGO = "PBKDF2WithHmacSHA256"

        private const val PREFS_NAME = "sf_secure_prefs"
        private const val KEY_SALT = "salt"
        private const val KEY_ENC_DB = "enc_db"
        private const val KEY_HASH = "pin_hash"
        private const val KEY_SETUP = "setup_done"
    }

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    val isSetup: Boolean get() = prefs.getBoolean(KEY_SETUP, false)

    /** Алғашқы орнату: кездейсоқ K_db + salt, PIN-мен шифрлау */
    fun setupNewUser(pin: CharArray): ByteArray {
        val salt = random(SALT_LEN)
        val kDb = random(32)
        val kUser = deriveKey(pin, salt)
        try {
            val encDb = encrypt(kDb, kUser)
            val hash = deriveKey(pin, salt + "v".toByteArray())
            prefs.edit()
                .putString(KEY_SALT, salt.b64())
                .putString(KEY_ENC_DB, encDb.b64())
                .putString(KEY_HASH, hash.b64())
                .putBoolean(KEY_SETUP, true)
                .commit()   // apply() емес, commit() — синхрондық сақтау
            return kDb.copyOf()
        } finally {
            kUser.fill(0)
        }
    }

    /** PIN тексеру → K_db немесе null */
    fun verifyPin(pin: CharArray): ByteArray? {
        val salt = prefs.getString(KEY_SALT, null)?.db64() ?: return null
        val encDb = prefs.getString(KEY_ENC_DB, null)?.db64() ?: return null
        val stored = prefs.getString(KEY_HASH, null)?.db64() ?: return null
        val kUser = deriveKey(pin, salt)
        return try {
            val hash = deriveKey(pin, salt + "v".toByteArray())
            if (!hash.ctEq(stored)) return null
            decrypt(encDb, kUser)
        } finally {
            kUser.fill(0)
        }
    }

    /** PIN ауыстыру */
    fun changePin(oldPin: CharArray, newPin: CharArray): Boolean {
        val kDb = verifyPin(oldPin) ?: return false
        try {
            val salt = random(SALT_LEN)
            val kUser = deriveKey(newPin, salt)
            try {
                val encDb = encrypt(kDb, kUser)
                val hash = deriveKey(newPin, salt + "v".toByteArray())
                prefs.edit()
                    .putString(KEY_SALT, salt.b64())
                    .putString(KEY_ENC_DB, encDb.b64())
                    .putString(KEY_HASH, hash.b64())
                    .commit()
                return true
            } finally {
                kUser.fill(0)
            }
        } finally {
            kDb.fill(0)
        }
    }

    /** Backup шифрлау (AES-256-GCM) */
    fun encryptBackup(data: ByteArray, password: CharArray): ByteArray {
        val salt = random(SALT_LEN)
        val key = deriveKey(password, salt)
        return try { salt + encrypt(data, key) } finally { key.fill(0) }
    }

    /** Backup дешифрлау */
    fun decryptBackup(data: ByteArray, password: CharArray): ByteArray? {
        if (data.size <= SALT_LEN) return null
        val key = deriveKey(password, data.copyOfRange(0, SALT_LEN))
        return try {
            decrypt(data.copyOfRange(SALT_LEN, data.size), key)
        } catch (e: Exception) { null } finally { key.fill(0) }
    }

    fun clearAll() = prefs.edit().clear().commit()

    // ── Internal helpers ──────────────────────────────────────────────────

    fun deriveKey(pin: CharArray, salt: ByteArray): ByteArray {
        val spec = PBEKeySpec(pin, salt, PBKDF2_ITERATIONS, KEY_BITS)
        return try {
            SecretKeyFactory.getInstance(PBKDF2_ALGO).generateSecret(spec).encoded
        } finally { spec.clearPassword() }
    }

    private fun encrypt(plain: ByteArray, key: ByteArray): ByteArray {
        val iv = random(IV_LEN)
        val c = Cipher.getInstance(AES_GCM)
        c.init(Cipher.ENCRYPT_MODE, SecretKeySpec(key, "AES"), GCMParameterSpec(GCM_TAG, iv))
        return iv + c.doFinal(plain)
    }

    private fun decrypt(data: ByteArray, key: ByteArray): ByteArray? {
        if (data.size <= IV_LEN) return null
        return try {
            val c = Cipher.getInstance(AES_GCM)
            c.init(Cipher.DECRYPT_MODE, SecretKeySpec(key, "AES"),
                GCMParameterSpec(GCM_TAG, data, 0, IV_LEN))
            c.doFinal(data, IV_LEN, data.size - IV_LEN)
        } catch (e: Exception) { null }
    }

    private fun random(n: Int) = ByteArray(n).also { SecureRandom().nextBytes(it) }
    private fun ByteArray.b64() = Base64.encodeToString(this, Base64.NO_WRAP)
    private fun String.db64() = Base64.decode(this, Base64.NO_WRAP)
    private fun ByteArray.ctEq(o: ByteArray): Boolean {
        if (size != o.size) return false
        var d = 0
        for (i in indices) d = d or (this[i].toInt() xor o[i].toInt())
        return d == 0
    }
}
