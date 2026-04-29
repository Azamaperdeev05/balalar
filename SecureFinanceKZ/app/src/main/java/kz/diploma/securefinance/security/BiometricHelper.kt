package kz.diploma.securefinance.security

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class BiometricHelper(context: Context) {

    companion object {
        private const val KEY_ALIAS = "sf_bio_key"
        private const val KEYSTORE = "AndroidKeyStore"
        private const val AES_GCM = "AES/GCM/NoPadding"
        private const val TAG_BITS = 128
        private const val PREFS = "sf_bio_prefs"
        private const val K_ENC = "bio_enc"
        private const val K_IV = "bio_iv"
        private const val K_ENABLED = "bio_on"
    }

    private val prefs: SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    val isBiometricEnabled: Boolean get() = prefs.getBoolean(K_ENABLED, false)

    fun isBiometricAvailable(context: Context): Boolean {
        val mgr = BiometricManager.from(context)
        return mgr.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) ==
                BiometricManager.BIOMETRIC_SUCCESS
    }

    fun isBiometricKeyInvalidated(): Boolean {
        return try {
            val ks = KeyStore.getInstance(KEYSTORE).also { it.load(null) }
            if (!ks.containsAlias(KEY_ALIAS)) return false
            val key = ks.getKey(KEY_ALIAS, null) as? SecretKey ?: return false
            Cipher.getInstance(AES_GCM).init(Cipher.ENCRYPT_MODE, key)
            false
        } catch (e: Exception) { true }
    }

    fun createBiometricKey() {
        val ks = KeyStore.getInstance(KEYSTORE).also { it.load(null) }
        if (ks.containsAlias(KEY_ALIAS)) ks.deleteEntry(KEY_ALIAS)
        val gen = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE)
        gen.init(KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setUserAuthenticationRequired(true)
            .setInvalidatedByBiometricEnrollment(true)
            .build()
        )
        gen.generateKey()
    }

    fun getEncryptCipher(): Cipher {
        val key = KeyStore.getInstance(KEYSTORE).also { it.load(null) }.getKey(KEY_ALIAS, null) as SecretKey
        return Cipher.getInstance(AES_GCM).also { it.init(Cipher.ENCRYPT_MODE, key) }
    }

    fun getDecryptCipher(): Cipher? {
        val ivB64 = prefs.getString(K_IV, null) ?: return null
        return try {
            val key = KeyStore.getInstance(KEYSTORE).also { it.load(null) }.getKey(KEY_ALIAS, null) as SecretKey
            val iv = Base64.decode(ivB64, Base64.NO_WRAP)
            Cipher.getInstance(AES_GCM).also {
                it.init(Cipher.DECRYPT_MODE, key, GCMParameterSpec(TAG_BITS, iv))
            }
        } catch (e: Exception) { null }
    }

    fun encryptAndStoreDbKey(cipher: Cipher, dbKey: ByteArray) {
        val enc = cipher.doFinal(dbKey)
        prefs.edit()
            .putString(K_ENC, Base64.encodeToString(enc, Base64.NO_WRAP))
            .putString(K_IV, Base64.encodeToString(cipher.iv, Base64.NO_WRAP))
            .putBoolean(K_ENABLED, true)
            .apply()
    }

    fun decryptDbKey(cipher: Cipher): ByteArray? {
        val enc = prefs.getString(K_ENC, null) ?: return null
        return try { cipher.doFinal(Base64.decode(enc, Base64.NO_WRAP)) } catch (e: Exception) { null }
    }

    fun authenticate(
        activity: FragmentActivity,
        cipher: Cipher,
        title: String,
        subtitle: String,
        negativeText: String,
        onSuccess: (Cipher) -> Unit,
        onError: (String) -> Unit
    ) {
        val prompt = BiometricPrompt(activity, ContextCompat.getMainExecutor(activity),
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    result.cryptoObject?.cipher?.let(onSuccess) ?: onError("Cipher null")
                }
                override fun onAuthenticationError(code: Int, msg: CharSequence) {
                    if (code != BiometricPrompt.ERROR_USER_CANCELED &&
                        code != BiometricPrompt.ERROR_NEGATIVE_BUTTON) onError(msg.toString())
                }
                override fun onAuthenticationFailed() {}
            })

        prompt.authenticate(
            BiometricPrompt.PromptInfo.Builder()
                .setTitle(title).setSubtitle(subtitle)
                .setNegativeButtonText(negativeText)
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
                .build(),
            BiometricPrompt.CryptoObject(cipher)
        )
    }

    fun disableBiometric() {
        prefs.edit().clear().apply()
        try {
            KeyStore.getInstance(KEYSTORE).also { it.load(null) }.deleteEntry(KEY_ALIAS)
        } catch (_: Exception) {}
    }
}
