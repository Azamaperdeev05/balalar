package kz.diploma.securefinance

import android.app.Application
import kz.diploma.securefinance.data.AppDatabase
import kz.diploma.securefinance.data.FinanceRepository
import kz.diploma.securefinance.security.BiometricHelper
import kz.diploma.securefinance.security.CryptoManager
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.ui.notification.NotificationHelper

class FinanceApp : Application() {

    val cryptoManager by lazy { CryptoManager(this) }
    val biometricHelper by lazy { BiometricHelper(this) }

    override fun onCreate() {
        super.onCreate()
        // net.zetetic:sqlcipher-android static initializer-да loadLibrary жоқ — қолмен жүктеу керек
        System.loadLibrary("sqlcipher")
        NotificationHelper.createChannels(this)
    }

    private var _db: AppDatabase? = null
    private var _repository: FinanceRepository? = null

    // null — DB ашылмаған (process death немесе lock кезінде)
    val repository: FinanceRepository? get() = _repository

    fun openDatabase(dbKey: ByteArray) {
        if (_db == null) {
            _db = AppDatabase.create(this, dbKey)
            _repository = FinanceRepository(_db!!)
        }
    }

    fun closeDatabase() {
        _db?.close()
        _db = null
        _repository = null
    }

    override fun onTerminate() {
        super.onTerminate()
        SessionManager.lock()
        closeDatabase()
    }
}
