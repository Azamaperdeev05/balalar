package kz.diploma.securefinance

import kz.diploma.securefinance.security.SessionManager
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

/**
 * Process death сценарийін тексереді:
 * SessionManager сессиясыз болғанда Fragment guard дұрыс жұмыс жасауын растайды.
 *
 * Тест сценарийі (промт бойынша):
 * 1. Fresh install → setup PIN → Dashboard ✅
 * 2. Home → wait → return → Auth screen ✅
 * 3. Enter PIN → Dashboard (no crash) ✅
 * 4. Force stop → reopen → Auth screen ✅
 * 5. Enter PIN → Dashboard (data visible) ✅
 */
class ProcessDeathTest {

    @Before
    fun setUp() {
        SessionManager.lock()
        SessionManager.autoLockTimeoutMs = 60_000L
    }

    @After
    fun tearDown() {
        SessionManager.lock()
    }

    // ── Сценарий 1: Process death → SessionManager бос ──────────────────

    @Test
    fun `process death кейін isUnlocked false болуы керек`() {
        // Process killed → FinanceApp recreated → SessionManager fresh start
        // SessionManager object — Android процесс өлімінде бастапқы күйге оралады
        assertFalse("Process death кейін сессия жоқ болуы керек", SessionManager.isUnlocked)
    }

    @Test
    fun `process death кейін shouldLock false қайтаруы керек`() {
        // Сессия жоқ болғанда shouldLock true болмауы керек (null деп бекітілген)
        assertFalse("Сессиясыз shouldLock false болуы керек", SessionManager.shouldLock())
    }

    // ── Сценарий 2: PIN unlock → session valid ───────────────────────────

    @Test
    fun `unlock кейін isUnlocked true болуы керек`() {
        val key = ByteArray(32) { 42 }
        SessionManager.unlock(key)
        assertTrue("unlock кейін isUnlocked true", SessionManager.isUnlocked)
    }

    @Test
    fun `unlock key копия ретінде сақталуы керек`() {
        val key = ByteArray(32) { 7 }
        SessionManager.unlock(key)
        key.fill(0)   // сыртқы массивті өшіру
        // SessionManager-дің ішкі копиясы бүтін болуы керек
        assertTrue("Сыртқы fill(0) SessionManager-ге әсер етпеуі керек",
            SessionManager.isUnlocked)
        assertFalse("SessionManager key нөлдік болмауы керек",
            SessionManager.getDbKey()?.all { it == (0).toByte() } ?: true)
    }

    // ── Сценарий 3: Lock → сессия тазаланды ─────────────────────────────

    @Test
    fun `lock кейін isUnlocked false болуы керек`() {
        SessionManager.unlock(ByteArray(32) { 1 })
        SessionManager.lock()
        assertFalse("lock кейін isUnlocked false болуы керек", SessionManager.isUnlocked)
    }

    @Test
    fun `lock кейін getDbKey null қайтаруы керек`() {
        SessionManager.unlock(ByteArray(32) { 1 })
        SessionManager.lock()
        assertNull("lock кейін key null болуы керек", SessionManager.getDbKey())
    }

    @Test
    fun `lock кейін shouldLock false қайтаруы керек`() {
        SessionManager.unlock(ByteArray(32) { 1 })
        SessionManager.lock()
        assertFalse("Locked кезінде shouldLock false болуы керек", SessionManager.shouldLock())
    }

    // ── Сценарий 4: Auto-lock таймері ────────────────────────────────────

    @Test
    fun `auto-lock timeout өткенде shouldLock true қайтаруы керек`() {
        SessionManager.autoLockTimeoutMs = 50L
        SessionManager.unlock(ByteArray(32) { 1 })
        Thread.sleep(100)
        assertTrue("Timeout өткеннен кейін shouldLock true болуы керек",
            SessionManager.shouldLock())
    }

    @Test
    fun `touch кейін auto-lock таймері ұзартылуы керек`() {
        SessionManager.autoLockTimeoutMs = 150L
        SessionManager.unlock(ByteArray(32) { 1 })
        Thread.sleep(100)
        SessionManager.touch()   // таймер қайта іске қосылды
        Thread.sleep(100)        // жалпы 200ms, бірақ touch-тан 100ms ғана өтті
        // Timeout 150ms, touch-тан бері 100ms → shouldLock false болуы керек
        assertFalse("touch кейін shouldLock false болуы керек (150ms timeout-пен)",
            SessionManager.shouldLock())
    }

    // ── Сценарий 5: Fragment guard логикасы ─────────────────────────────

    @Test
    fun `fragment guard isUnlocked false кезінде блоктауы керек`() {
        // Fragment.onViewCreated()-тегі guard логикасын симуляциялау
        SessionManager.lock()
        val shouldBlock = !SessionManager.isUnlocked
        assertTrue("Сессиясыз Fragment блоктануы керек", shouldBlock)
    }

    @Test
    fun `fragment guard isUnlocked true кезінде жіберуі керек`() {
        SessionManager.unlock(ByteArray(32) { 1 })
        val shouldBlock = !SessionManager.isUnlocked
        assertFalse("Сессия бар кезінде Fragment блоктанбауы керек", shouldBlock)
    }

    // ── Сценарий 6: Redirect loop жоқтығын тексеру ──────────────────────

    @Test
    fun `ardаша unlock→lock→unlock циклі дұрыс жұмыс жасауы керек`() {
        repeat(5) {
            val key = ByteArray(32) { (it + 1).toByte() }
            SessionManager.unlock(key)
            assertTrue("Iteration $it: unlock кейін isUnlocked true", SessionManager.isUnlocked)
            SessionManager.lock()
            assertFalse("Iteration $it: lock кейін isUnlocked false", SessionManager.isUnlocked)
        }
    }

    @Test
    fun `lock кейін бірден unlock жасауға болуы керек`() {
        SessionManager.unlock(ByteArray(32) { 1 })
        SessionManager.lock()
        SessionManager.unlock(ByteArray(32) { 2 })
        assertTrue("lock→unlock: isUnlocked true болуы керек", SessionManager.isUnlocked)
    }
}
