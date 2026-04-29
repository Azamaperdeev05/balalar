package kz.diploma.securefinance.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val type: String,
    val color: Int
) {
    companion object {
        const val TYPE_INCOME = "INCOME"
        const val TYPE_EXPENSE = "EXPENSE"
    }
}
