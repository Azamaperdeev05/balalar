package kz.diploma.securefinance.security;

/**
 * ТЗ 8.2: PIN → PBKDF2 → K_user → AES-GCM → Enc(K_db).
 * Деректер қарапайым SharedPreferences-та сақталады (AES-GCM шифрмен қорғалған).
 * EncryptedSharedPreferences алынып тасталды — кейбір құрылғыларда Keystore init crash береді.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 &2\u00020\u0001:\u0001&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000e\u001a\u00020\u0006J\u001a\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\fJ\u0016\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0010J\u0018\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002J\u0016\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\fJ\u0010\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u000e\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\fJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0016\u001a\u00020\fJ\u0014\u0010 \u001a\n \"*\u0004\u0018\u00010!0!*\u00020\u0010H\u0002J\u0014\u0010#\u001a\u00020\u0006*\u00020\u00102\u0006\u0010$\u001a\u00020\u0010H\u0002J\u0014\u0010%\u001a\n \"*\u0004\u0018\u00010\u00100\u0010*\u00020!H\u0002R\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lkz/diploma/securefinance/security/CryptoManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isSetup", "", "()Z", "prefs", "Landroid/content/SharedPreferences;", "changePin", "oldPin", "", "newPin", "clearAll", "decrypt", "", "data", "key", "decryptBackup", "password", "deriveKey", "pin", "salt", "encrypt", "plain", "encryptBackup", "random", "n", "", "setupNewUser", "verifyPin", "b64", "", "kotlin.jvm.PlatformType", "ctEq", "o", "db64", "Companion", "app_release"})
public final class CryptoManager {
    private static final int PBKDF2_ITERATIONS = 100000;
    private static final int KEY_BITS = 256;
    private static final int SALT_LEN = 16;
    private static final int IV_LEN = 12;
    private static final int GCM_TAG = 128;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String AES_GCM = "AES/GCM/NoPadding";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PBKDF2_ALGO = "PBKDF2WithHmacSHA256";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "sf_secure_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SALT = "salt";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ENC_DB = "enc_db";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_HASH = "pin_hash";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SETUP = "setup_done";
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    public static final kz.diploma.securefinance.security.CryptoManager.Companion Companion = null;
    
    public CryptoManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean isSetup() {
        return false;
    }
    
    /**
     * Алғашқы орнату: кездейсоқ K_db + salt, PIN-мен шифрлау
     */
    @org.jetbrains.annotations.NotNull()
    public final byte[] setupNewUser(@org.jetbrains.annotations.NotNull()
    char[] pin) {
        return null;
    }
    
    /**
     * PIN тексеру → K_db немесе null
     */
    @org.jetbrains.annotations.Nullable()
    public final byte[] verifyPin(@org.jetbrains.annotations.NotNull()
    char[] pin) {
        return null;
    }
    
    /**
     * PIN ауыстыру
     */
    public final boolean changePin(@org.jetbrains.annotations.NotNull()
    char[] oldPin, @org.jetbrains.annotations.NotNull()
    char[] newPin) {
        return false;
    }
    
    /**
     * Backup шифрлау (AES-256-GCM)
     */
    @org.jetbrains.annotations.NotNull()
    public final byte[] encryptBackup(@org.jetbrains.annotations.NotNull()
    byte[] data, @org.jetbrains.annotations.NotNull()
    char[] password) {
        return null;
    }
    
    /**
     * Backup дешифрлау
     */
    @org.jetbrains.annotations.Nullable()
    public final byte[] decryptBackup(@org.jetbrains.annotations.NotNull()
    byte[] data, @org.jetbrains.annotations.NotNull()
    char[] password) {
        return null;
    }
    
    public final boolean clearAll() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] deriveKey(@org.jetbrains.annotations.NotNull()
    char[] pin, @org.jetbrains.annotations.NotNull()
    byte[] salt) {
        return null;
    }
    
    private final byte[] encrypt(byte[] plain, byte[] key) {
        return null;
    }
    
    private final byte[] decrypt(byte[] data, byte[] key) {
        return null;
    }
    
    private final byte[] random(int n) {
        return null;
    }
    
    private final java.lang.String b64(byte[] $this$b64) {
        return null;
    }
    
    private final byte[] db64(java.lang.String $this$db64) {
        return null;
    }
    
    private final boolean ctEq(byte[] $this$ctEq, byte[] o) {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lkz/diploma/securefinance/security/CryptoManager$Companion;", "", "()V", "AES_GCM", "", "GCM_TAG", "", "IV_LEN", "KEY_BITS", "KEY_ENC_DB", "KEY_HASH", "KEY_SALT", "KEY_SETUP", "PBKDF2_ALGO", "PBKDF2_ITERATIONS", "PREFS_NAME", "SALT_LEN", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}