package kz.diploma.securefinance.security

/**
 * Сессия кілтін (K_db) жадта сақтайды.
 * lock() міндетті түрде:
 *   1) dbKey?.fill(0)  — жадтан өшіру
 *   2) dbKey = null    — null-ге орнату
 * AppDatabase.close() FinanceApp.closeDatabase() арқылы қоса шақырылады.
 */
object SessionManager {

    private var _dbKey: ByteArray? = null
    private var lastActiveMs: Long = 0L
    var autoLockTimeoutMs: Long = 60_000L

    /** Сессия ашық па */
    val isUnlocked: Boolean get() = _dbKey != null

    fun unlock(key: ByteArray) {
        _dbKey?.fill(0)
        _dbKey = key.copyOf()
        lastActiveMs = now()
    }

    /** Кілтті жадтан толық өшіреді */
    fun lock() {
        _dbKey?.fill(0)
        _dbKey = null
        lastActiveMs = 0L
    }

    fun getDbKey(): ByteArray? = _dbKey

    fun touch() {
        if (_dbKey != null) lastActiveMs = now()
    }

    /**
     * Автоқұлыптау уақыты таусылды ма?
     * Тек сессия ашық және timeout > 0 болғанда мүмкін.
     */
    fun shouldLock(): Boolean {
        if (_dbKey == null) return false
        if (autoLockTimeoutMs <= 0) return false
        return (now() - lastActiveMs) >= autoLockTimeoutMs
    }

    private fun now() = System.currentTimeMillis()
}
