package kz.diploma.securefinance

import kz.diploma.securefinance.security.SessionManager
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SessionManagerTest {

    @Before
    fun setUp() {
        SessionManager.lock()
        SessionManager.autoLockTimeoutMs = 60_000L
    }

    @After
    fun tearDown() {
        SessionManager.lock()
    }

    @Test
    fun `Бастапқыда қосымша құлыпталған болуы керек`() {
        assertFalse("Бастапқы күйде isUnlocked false болуы керек", SessionManager.isUnlocked)
    }

    @Test
    fun `unlock шақырылғаннан кейін isUnlocked true болуы керек`() {
        val key = ByteArray(32) { 1 }
        SessionManager.unlock(key)
        assertTrue("unlock-тан кейін isUnlocked true болуы керек", SessionManager.isUnlocked)
    }

    @Test
    fun `lock шақырылғаннан кейін isUnlocked false болуы керек`() {
        val key = ByteArray(32) { 1 }
        SessionManager.unlock(key)
        SessionManager.lock()
        assertFalse("lock-тан кейін isUnlocked false болуы керек", SessionManager.isUnlocked)
    }

    @Test
    fun `lock кілтті жадтан өшіруі керек`() {
        val key = ByteArray(32) { 42 }
        SessionManager.unlock(key)
        SessionManager.lock()
        assertNull("lock-тан кейін getDbKey null қайтаруы керек", SessionManager.getDbKey())
    }

    @Test
    fun `shouldLock timeout өткенде true қайтаруы керек`() {
        val key = ByteArray(32) { 1 }
        SessionManager.autoLockTimeoutMs = 100L
        SessionManager.unlock(key)
        Thread.sleep(200)
        assertTrue("Timeout өткеннен кейін shouldLock true болуы керек", SessionManager.shouldLock())
    }

    @Test
    fun `shouldLock timeout өтпесе false қайтаруы керек`() {
        val key = ByteArray(32) { 1 }
        SessionManager.autoLockTimeoutMs = 60_000L
        SessionManager.unlock(key)
        assertFalse("Timeout өтпеген кезде shouldLock false болуы керек", SessionManager.shouldLock())
    }

    @Test
    fun `touch lastActive уақытын жаңартуы керек`() {
        val key = ByteArray(32) { 1 }
        SessionManager.autoLockTimeoutMs = 200L
        SessionManager.unlock(key)
        Thread.sleep(150)
        SessionManager.touch()
        Thread.sleep(100)
        // touch жасалғаннан кейін 100ms өтті, timeout 200ms → әлі lock болмауы керек
        assertFalse("touch кейін shouldLock false болуы керек", SessionManager.shouldLock())
    }

    @Test
    fun `Құлыпталған кезде shouldLock false қайтаруы керек`() {
        assertFalse("Құлыпталған кезде shouldLock false болуы керек", SessionManager.shouldLock())
    }

    @Test
    fun `autoLockTimeoutMs нөл немесе теріс болса shouldLock ешқашан true болмауы керек`() {
        val key = ByteArray(32) { 1 }
        SessionManager.autoLockTimeoutMs = -1L
        SessionManager.unlock(key)
        Thread.sleep(100)
        assertFalse("autoLockTimeoutMs <= 0 болса shouldLock false болуы керек", SessionManager.shouldLock())
    }
}
