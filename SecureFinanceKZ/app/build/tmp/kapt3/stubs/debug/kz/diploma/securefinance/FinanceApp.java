package kz.diploma.securefinance;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0016H\u0016J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u001bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\u000e8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\f\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00068F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u001c"}, d2 = {"Lkz/diploma/securefinance/FinanceApp;", "Landroid/app/Application;", "()V", "_db", "Lkz/diploma/securefinance/data/AppDatabase;", "_repository", "Lkz/diploma/securefinance/data/FinanceRepository;", "biometricHelper", "Lkz/diploma/securefinance/security/BiometricHelper;", "getBiometricHelper", "()Lkz/diploma/securefinance/security/BiometricHelper;", "biometricHelper$delegate", "Lkotlin/Lazy;", "cryptoManager", "Lkz/diploma/securefinance/security/CryptoManager;", "getCryptoManager", "()Lkz/diploma/securefinance/security/CryptoManager;", "cryptoManager$delegate", "repository", "getRepository", "()Lkz/diploma/securefinance/data/FinanceRepository;", "closeDatabase", "", "onCreate", "onTerminate", "openDatabase", "dbKey", "", "app_debug"})
public final class FinanceApp extends android.app.Application {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy cryptoManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy biometricHelper$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private kz.diploma.securefinance.data.AppDatabase _db;
    @org.jetbrains.annotations.Nullable()
    private kz.diploma.securefinance.data.FinanceRepository _repository;
    
    public FinanceApp() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kz.diploma.securefinance.security.CryptoManager getCryptoManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kz.diploma.securefinance.security.BiometricHelper getBiometricHelper() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final kz.diploma.securefinance.data.FinanceRepository getRepository() {
        return null;
    }
    
    public final void openDatabase(@org.jetbrains.annotations.NotNull()
    byte[] dbKey) {
    }
    
    public final void closeDatabase() {
    }
    
    @java.lang.Override()
    public void onTerminate() {
    }
}