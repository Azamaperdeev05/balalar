package kz.diploma.securefinance.security;

/**
 * Сессия кілтін (K_db) жадта сақтайды.
 * lock() міндетті түрде:
 *  1) dbKey?.fill(0)  — жадтан өшіру
 *  2) dbKey = null    — null-ге орнату
 * AppDatabase.close() FinanceApp.closeDatabase() арқылы қоса шақырылады.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\b\u0010\u0012\u001a\u00020\u0006H\u0002J\u0006\u0010\u0013\u001a\u00020\fJ\u0006\u0010\u0014\u001a\u00020\u0011J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lkz/diploma/securefinance/security/SessionManager;", "", "()V", "_dbKey", "", "autoLockTimeoutMs", "", "getAutoLockTimeoutMs", "()J", "setAutoLockTimeoutMs", "(J)V", "isUnlocked", "", "()Z", "lastActiveMs", "getDbKey", "lock", "", "now", "shouldLock", "touch", "unlock", "key", "app_release"})
public final class SessionManager {
    @org.jetbrains.annotations.Nullable()
    private static byte[] _dbKey;
    private static long lastActiveMs = 0L;
    private static long autoLockTimeoutMs = 60000L;
    @org.jetbrains.annotations.NotNull()
    public static final kz.diploma.securefinance.security.SessionManager INSTANCE = null;
    
    private SessionManager() {
        super();
    }
    
    public final long getAutoLockTimeoutMs() {
        return 0L;
    }
    
    public final void setAutoLockTimeoutMs(long p0) {
    }
    
    public final boolean isUnlocked() {
        return false;
    }
    
    public final void unlock(@org.jetbrains.annotations.NotNull()
    byte[] key) {
    }
    
    /**
     * Кілтті жадтан толық өшіреді
     */
    public final void lock() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final byte[] getDbKey() {
        return null;
    }
    
    public final void touch() {
    }
    
    /**
     * Автоқұлыптау уақыты таусылды ма?
     * Тек сессия ашық және timeout > 0 болғанда мүмкін.
     */
    public final boolean shouldLock() {
        return false;
    }
    
    private final long now() {
        return 0L;
    }
}