package kz.diploma.securefinance.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "accounts")
data class Account(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: String,
    val balance: Double = 0.0,
    val currency: String = "KZT",
    val color: Int,
    val createdAt: Long = System.currentTimeMillis()
) {
    companion object {
        const val TYPE_CASH = "CASH"
        const val TYPE_CARD = "CARD"
        const val TYPE_SAVINGS = "SAVINGS"
        const val TYPE_OTHER = "OTHER"
    }
}
