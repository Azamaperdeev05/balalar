package kz.diploma.securefinance.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kz.diploma.securefinance.data.entity.Transaction

data class CategoryStat(
    val categoryId: Long?,
    val categoryName: String?,
    val total: Double,
    val color: Int?
)

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    fun getAllLive(): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions ORDER BY date DESC")
    suspend fun getAll(): List<Transaction>

    @Query("SELECT * FROM transactions WHERE id = :id")
    suspend fun getById(id: Long): Transaction?

    @Query("""
        SELECT * FROM transactions
        WHERE (:fromDate IS NULL OR date >= :fromDate)
          AND (:toDate IS NULL OR date <= :toDate)
          AND (:type IS NULL OR type = :type)
          AND (:accountId IS NULL OR accountId = :accountId)
          AND (:categoryId IS NULL OR categoryId = :categoryId)
          AND (:query IS NULL OR note LIKE '%' || :query || '%')
        ORDER BY date DESC
    """)
    fun getFilteredLive(
        fromDate: Long? = null,
        toDate: Long? = null,
        type: String? = null,
        accountId: Long? = null,
        categoryId: Long? = null,
        query: String? = null
    ): LiveData<List<Transaction>>

    @Query("SELECT * FROM transactions ORDER BY date DESC LIMIT :limit")
    fun getRecentLive(limit: Int = 10): LiveData<List<Transaction>>

    @Query("""
        SELECT IFNULL(SUM(amount), 0.0) FROM transactions
        WHERE type = :type AND date >= :fromDate AND date <= :toDate
    """)
    fun getSumByTypeLive(type: String, fromDate: Long, toDate: Long): LiveData<Double>

    @Query("""
        SELECT t.categoryId, c.name AS categoryName, SUM(t.amount) AS total, c.color
        FROM transactions t
        LEFT JOIN categories c ON t.categoryId = c.id
        WHERE t.type = 'EXPENSE'
          AND t.date >= :fromDate AND t.date <= :toDate
        GROUP BY t.categoryId
        ORDER BY total DESC
    """)
    fun getCategoryStatsLive(fromDate: Long, toDate: Long): LiveData<List<CategoryStat>>

    @Query("""
        SELECT t.categoryId, c.name AS categoryName, SUM(t.amount) AS total, c.color
        FROM transactions t
        LEFT JOIN categories c ON t.categoryId = c.id
        WHERE t.type = 'EXPENSE'
          AND t.date >= :fromDate AND t.date <= :toDate
        GROUP BY t.categoryId
        ORDER BY total DESC
    """)
    suspend fun getCategoryStats(fromDate: Long, toDate: Long): List<CategoryStat>

    @Query("""
        SELECT * FROM transactions
        WHERE date >= :fromDate AND date <= :toDate
        ORDER BY date ASC
    """)
    suspend fun getByDateRange(fromDate: Long, toDate: Long): List<Transaction>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transactions: List<Transaction>)

    @Update
    suspend fun update(transaction: Transaction)

    @Delete
    suspend fun delete(transaction: Transaction)

    @Query("DELETE FROM transactions")
    suspend fun deleteAll()
}
