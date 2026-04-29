package kz.diploma.securefinance.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = Account::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index("accountId"),
        Index("categoryId"),
        Index("date")
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val amount: Double,
    val type: String,
    val accountId: Long,
    val categoryId: Long?,
    val note: String = "",
    val date: Long = System.currentTimeMillis()
) {
    companion object {
        const val TYPE_INCOME = "INCOME"
        const val TYPE_EXPENSE = "EXPENSE"
    }
}
