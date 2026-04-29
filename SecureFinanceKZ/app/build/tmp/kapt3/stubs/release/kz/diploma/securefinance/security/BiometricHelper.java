package kz.diploma.securefinance.security;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\t\u0018\u0000 !2\u00020\u0001:\u0001!B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004JV\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b0\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u0015J\u0006\u0010\u0017\u001a\u00020\u000bJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u001a\u001a\u00020\u000bJ\u0016\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u0019J\b\u0010\u001d\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010\u001e\u001a\u00020\u000fJ\u000e\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0003J\u0006\u0010 \u001a\u00020\u0006R\u0011\u0010\u0005\u001a\u00020\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lkz/diploma/securefinance/security/BiometricHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "isBiometricEnabled", "", "()Z", "prefs", "Landroid/content/SharedPreferences;", "authenticate", "", "activity", "Landroidx/fragment/app/FragmentActivity;", "cipher", "Ljavax/crypto/Cipher;", "title", "", "subtitle", "negativeText", "onSuccess", "Lkotlin/Function1;", "onError", "createBiometricKey", "decryptDbKey", "", "disableBiometric", "encryptAndStoreDbKey", "dbKey", "getDecryptCipher", "getEncryptCipher", "isBiometricAvailable", "isBiometricKeyInvalidated", "Companion", "app_release"})
public final class BiometricHelper {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ALIAS = "sf_bio_key";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEYSTORE = "AndroidKeyStore";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String AES_GCM = "AES/GCM/NoPadding";
    private static final int TAG_BITS = 128;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS = "sf_bio_prefs";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String K_ENC = "bio_enc";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String K_IV = "bio_iv";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String K_ENABLED = "bio_on";
    @org.jetbrains.annotations.NotNull()
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    public static final kz.diploma.securefinance.security.BiometricHelper.Companion Companion = null;
    
    public BiometricHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    public final boolean isBiometricEnabled() {
        return false;
    }
    
    public final boolean isBiometricAvailable(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean isBiometricKeyInvalidated() {
        return false;
    }
    
    public final void createBiometricKey() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final javax.crypto.Cipher getEncryptCipher() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final javax.crypto.Cipher getDecryptCipher() {
        return null;
    }
    
    public final void encryptAndStoreDbKey(@org.jetbrains.annotations.NotNull()
    javax.crypto.Cipher cipher, @org.jetbrains.annotations.NotNull()
    byte[] dbKey) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final byte[] decryptDbKey(@org.jetbrains.annotations.NotNull()
    javax.crypto.Cipher cipher) {
        return null;
    }
    
    public final void authenticate(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentActivity activity, @org.jetbrains.annotations.NotNull()
    javax.crypto.Cipher cipher, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String subtitle, @org.jetbrains.annotations.NotNull()
    java.lang.String negativeText, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super javax.crypto.Cipher, kotlin.Unit> onSuccess, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError) {
    }
    
    public final void disableBiometric() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lkz/diploma/securefinance/security/BiometricHelper$Companion;", "", "()V", "AES_GCM", "", "KEYSTORE", "KEY_ALIAS", "K_ENABLED", "K_ENC", "K_IV", "PREFS", "TAG_BITS", "", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}