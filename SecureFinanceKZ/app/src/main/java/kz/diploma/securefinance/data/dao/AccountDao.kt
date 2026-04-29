package kz.diploma.securefinance.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import kz.diploma.securefinance.data.entity.Account

@Dao
interface AccountDao {

    @Query("SELECT * FROM accounts ORDER BY createdAt ASC")
    fun getAllLive(): LiveData<List<Account>>

    @Query("SELECT * FROM accounts ORDER BY createdAt ASC")
    suspend fun getAll(): List<Account>

    @Query("SELECT * FROM accounts WHERE id = :id")
    suspend fun getById(id: Long): Account?

    @Query("SELECT IFNULL(SUM(balance), 0.0) FROM accounts")
    fun getTotalBalanceLive(): LiveData<Double>

    @Query("SELECT IFNULL(SUM(balance), 0.0) FROM accounts")
    suspend fun getTotalBalance(): Double

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(account: Account): Long

    @Update
    suspend fun update(account: Account)

    @Delete
    suspend fun delete(account: Account)

    @Query("UPDATE accounts SET balance = balance + :delta WHERE id = :accountId")
    suspend fun adjustBalance(accountId: Long, delta: Double)

    @Query("UPDATE accounts SET balance = :newBalance WHERE id = :accountId")
    suspend fun setBalance(accountId: Long, newBalance: Double)
}
