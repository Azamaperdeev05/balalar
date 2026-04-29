package kz.diploma.securefinance.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exchange_rates")
data class ExchangeRate(
    @PrimaryKey val currency: String,
    val rateToKzt: Double,
    val updatedAt: Long = System.currentTimeMillis()
)
