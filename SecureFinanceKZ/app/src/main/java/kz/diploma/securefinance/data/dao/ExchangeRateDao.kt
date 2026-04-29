package kz.diploma.securefinance.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.diploma.securefinance.data.entity.ExchangeRate

@Dao
interface ExchangeRateDao {
    @Query("SELECT * FROM exchange_rates")
    fun getAllLive(): LiveData<List<ExchangeRate>>

    @Query("SELECT * FROM exchange_rates")
    suspend fun getAll(): List<ExchangeRate>

    @Query("SELECT * FROM exchange_rates WHERE currency = :currency LIMIT 1")
    suspend fun getByCurrency(currency: String): ExchangeRate?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(rate: ExchangeRate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(rates: List<ExchangeRate>)
}
