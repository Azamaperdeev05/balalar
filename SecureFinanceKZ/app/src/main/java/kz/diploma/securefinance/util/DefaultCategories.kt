package kz.diploma.securefinance.util

import android.content.Context
import android.graphics.Color
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Category

object DefaultCategories {

    fun create(context: Context): List<Category> {
        val res = context.resources
        return listOf(
            // Шығыс категориялары
            Category(name = res.getString(R.string.cat_food), type = Category.TYPE_EXPENSE, color = Color.parseColor("#FF7043")),
            Category(name = res.getString(R.string.cat_transport), type = Category.TYPE_EXPENSE, color = Color.parseColor("#42A5F5")),
            Category(name = res.getString(R.string.cat_housing), type = Category.TYPE_EXPENSE, color = Color.parseColor("#AB47BC")),
            Category(name = res.getString(R.string.cat_communication), type = Category.TYPE_EXPENSE, color = Color.parseColor("#26C6DA")),
            Category(name = res.getString(R.string.cat_clothing), type = Category.TYPE_EXPENSE, color = Color.parseColor("#EC407A")),
            Category(name = res.getString(R.string.cat_health), type = Category.TYPE_EXPENSE, color = Color.parseColor("#EF5350")),
            Category(name = res.getString(R.string.cat_entertainment), type = Category.TYPE_EXPENSE, color = Color.parseColor("#FFA726")),
            Category(name = res.getString(R.string.cat_education), type = Category.TYPE_EXPENSE, color = Color.parseColor("#5C6BC0")),
            Category(name = res.getString(R.string.cat_restaurant), type = Category.TYPE_EXPENSE, color = Color.parseColor("#FF8F00")),
            Category(name = res.getString(R.string.cat_gifts), type = Category.TYPE_EXPENSE, color = Color.parseColor("#E91E63")),
            Category(name = res.getString(R.string.cat_taxes), type = Category.TYPE_EXPENSE, color = Color.parseColor("#78909C")),
            Category(name = res.getString(R.string.cat_other_expense), type = Category.TYPE_EXPENSE, color = Color.parseColor("#90A4AE")),

            // Кіріс категориялары
            Category(name = res.getString(R.string.cat_salary), type = Category.TYPE_INCOME, color = Color.parseColor("#4CAF50")),
            Category(name = res.getString(R.string.cat_bonus), type = Category.TYPE_INCOME, color = Color.parseColor("#8BC34A")),
            Category(name = res.getString(R.string.cat_freelance), type = Category.TYPE_INCOME, color = Color.parseColor("#00BCD4")),
            Category(name = res.getString(R.string.cat_pension), type = Category.TYPE_INCOME, color = Color.parseColor("#607D8B")),
            Category(name = res.getString(R.string.cat_investment), type = Category.TYPE_INCOME, color = Color.parseColor("#FF9800")),
            Category(name = res.getString(R.string.cat_debt_return), type = Category.TYPE_INCOME, color = Color.parseColor("#9C27B0")),
            Category(name = res.getString(R.string.cat_other_income), type = Category.TYPE_INCOME, color = Color.parseColor("#795548"))
        )
    }
}
