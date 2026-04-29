package kz.diploma.securefinance.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kz.diploma.securefinance.data.dao.AccountDao
import kz.diploma.securefinance.data.dao.CategoryDao
import kz.diploma.securefinance.data.dao.ExchangeRateDao
import kz.diploma.securefinance.data.dao.TransactionDao
import kz.diploma.securefinance.data.entity.Account
import kz.diploma.securefinance.data.entity.Category
import kz.diploma.securefinance.data.entity.ExchangeRate
import kz.diploma.securefinance.data.entity.Transaction
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory

@Database(
    entities = [Account::class, Category::class, Transaction::class, ExchangeRate::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun categoryDao(): CategoryDao
    abstract fun transactionDao(): TransactionDao
    abstract fun exchangeRateDao(): ExchangeRateDao

    companion object {
        private const val DB_NAME = "securefinance.db"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """CREATE TABLE IF NOT EXISTS exchange_rates (
                        currency TEXT NOT NULL PRIMARY KEY,
                        rateToKzt REAL NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )"""
                )
                val now = System.currentTimeMillis()
                database.execSQL("INSERT OR IGNORE INTO exchange_rates VALUES ('USD', 460.0, $now)")
                database.execSQL("INSERT OR IGNORE INTO exchange_rates VALUES ('EUR', 500.0, $now)")
                database.execSQL("INSERT OR IGNORE INTO exchange_rates VALUES ('RUB', 5.0, $now)")
            }
        }

        fun create(context: Context, passphrase: ByteArray): AppDatabase {
            val factory = SupportOpenHelperFactory(passphrase.copyOf())
            return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .openHelperFactory(factory)
                .addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}
