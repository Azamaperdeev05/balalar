package kz.diploma.securefinance.data

import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import kz.diploma.securefinance.data.dao.CategoryStat
import kz.diploma.securefinance.data.entity.Account
import kz.diploma.securefinance.data.entity.Category
import kz.diploma.securefinance.data.entity.ExchangeRate
import kz.diploma.securefinance.data.entity.Transaction

class FinanceRepository(private val db: AppDatabase) {

    // --- Accounts ---

    fun getAllAccountsLive(): LiveData<List<Account>> = db.accountDao().getAllLive()

    suspend fun getAllAccounts(): List<Account> = db.accountDao().getAll()

    fun getTotalBalanceLive(): LiveData<Double> = db.accountDao().getTotalBalanceLive()

    suspend fun addAccount(account: Account): Long = db.accountDao().insert(account)

    suspend fun updateAccount(account: Account) = db.accountDao().update(account)

    suspend fun deleteAccount(account: Account) = db.accountDao().delete(account)

    suspend fun getAccountById(id: Long): Account? = db.accountDao().getById(id)

    // --- Categories ---

    fun getAllCategoriesLive(): LiveData<List<Category>> = db.categoryDao().getAllLive()

    suspend fun getAllCategories(): List<Category> = db.categoryDao().getAll()

    fun getCategoriesByTypeLive(type: String): LiveData<List<Category>> =
        db.categoryDao().getByTypeLive(type)

    suspend fun getCategoriesByType(type: String): List<Category> =
        db.categoryDao().getByType(type)

    suspend fun addCategory(category: Category): Long = db.categoryDao().insert(category)

    suspend fun updateCategory(category: Category) = db.categoryDao().update(category)

    suspend fun deleteCategory(category: Category) = db.categoryDao().delete(category)

    suspend fun getCategoryCount(): Int = db.categoryDao().getCount()

    // --- Transactions ---

    fun getAllTransactionsLive(): LiveData<List<Transaction>> = db.transactionDao().getAllLive()

    fun getFilteredTransactionsLive(
        fromDate: Long? = null,
        toDate: Long? = null,
        type: String? = null,
        accountId: Long? = null,
        categoryId: Long? = null,
        query: String? = null
    ): LiveData<List<Transaction>> = db.transactionDao().getFilteredLive(
        fromDate, toDate, type, accountId, categoryId, query
    )

    fun getRecentTransactionsLive(limit: Int = 10): LiveData<List<Transaction>> =
        db.transactionDao().getRecentLive(limit)

    fun getMonthSumLive(type: String, fromDate: Long, toDate: Long): LiveData<Double> =
        db.transactionDao().getSumByTypeLive(type, fromDate, toDate)

    fun getCategoryStatsLive(fromDate: Long, toDate: Long): LiveData<List<CategoryStat>> =
        db.transactionDao().getCategoryStatsLive(fromDate, toDate)

    suspend fun getCategoryStats(fromDate: Long, toDate: Long): List<CategoryStat> =
        db.transactionDao().getCategoryStats(fromDate, toDate)

    suspend fun getTransactionsByDateRange(from: Long, to: Long): List<Transaction> =
        db.transactionDao().getByDateRange(from, to)

    suspend fun addTransaction(transaction: Transaction): Long {
        return db.withTransaction {
            val id = db.transactionDao().insert(transaction)
            val delta = if (transaction.type == Transaction.TYPE_INCOME) transaction.amount
                        else -transaction.amount
            db.accountDao().adjustBalance(transaction.accountId, delta)
            id
        }
    }

    suspend fun updateTransaction(old: Transaction, new: Transaction) {
        db.withTransaction {
            // Ескі мәнді қайтару
            val oldDelta = if (old.type == Transaction.TYPE_INCOME) -old.amount else old.amount
            db.accountDao().adjustBalance(old.accountId, oldDelta)

            // Шот өзгерген болса, ескі шотқа қайтару жоғарыда жасалды; жаңа шотқа қолдану
            val newDelta = if (new.type == Transaction.TYPE_INCOME) new.amount else -new.amount
            db.accountDao().adjustBalance(new.accountId, newDelta)

            db.transactionDao().update(new)
        }
    }

    suspend fun deleteTransaction(transaction: Transaction) {
        db.withTransaction {
            val delta = if (transaction.type == Transaction.TYPE_INCOME) -transaction.amount
                        else transaction.amount
            db.accountDao().adjustBalance(transaction.accountId, delta)
            db.transactionDao().delete(transaction)
        }
    }

    suspend fun getTransactionById(id: Long): Transaction? = db.transactionDao().getById(id)

    // --- Backup / Restore ---

    suspend fun getAllTransactions(): List<Transaction> = db.transactionDao().getAll()

    suspend fun clearAndRestore(
        accounts: List<Account>,
        categories: List<Category>,
        transactions: List<Transaction>
    ) {
        db.withTransaction {
            db.transactionDao().deleteAll()
            // Room CASCADE жоймайды, сондықтан транзакцияларды алдымен жойдық
            db.categoryDao().insertAll(categories)
            val accountIds = mutableMapOf<Long, Long>()
            accounts.forEach { acc ->
                val newId = db.accountDao().insert(acc.copy(id = 0, balance = 0.0))
                accountIds[acc.id] = newId
            }
            transactions.forEach { tx ->
                val newAccountId = accountIds[tx.accountId] ?: tx.accountId
                val id = db.transactionDao().insert(tx.copy(id = 0, accountId = newAccountId))
                val delta = if (tx.type == Transaction.TYPE_INCOME) tx.amount else -tx.amount
                db.accountDao().adjustBalance(newAccountId, delta)
            }
        }
    }

    suspend fun insertDefaultCategories(categories: List<Category>) {
        db.categoryDao().insertAll(categories)
    }

    // --- Exchange Rates ---

    fun getExchangeRatesLive(): LiveData<List<ExchangeRate>> = db.exchangeRateDao().getAllLive()

    suspend fun getExchangeRates(): List<ExchangeRate> = db.exchangeRateDao().getAll()

    suspend fun upsertExchangeRate(rate: ExchangeRate) = db.exchangeRateDao().upsert(rate)

    suspend fun getTotalBalance(): Double = db.accountDao().getTotalBalance()
}
