package kz.diploma.securefinance.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.google.android.material.snackbar.Snackbar
import kz.diploma.securefinance.R
import kz.diploma.securefinance.data.entity.Transaction
import kz.diploma.securefinance.ui.transactions.TransactionItem
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Activity.enableFlagSecure() {
    window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE)
}

fun Activity.disableFlagSecure() {
    window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSnackbar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun Double.formatMoney(currency: String = "KZT"): String {
    val locale = Locale("kk", "KZ")
    val fmt = NumberFormat.getNumberInstance(locale)
    fmt.maximumFractionDigits = 2
    fmt.minimumFractionDigits = 0
    val symbol = when (currency) {
        "KZT" -> "₸"
        "USD" -> "$"
        "EUR" -> "€"
        "RUB" -> "₽"
        else -> currency
    }
    return "${fmt.format(this)} $symbol"
}

fun Long.toFormattedDate(): String {
    val locale = Locale("kk", "KZ")
    val sdf = SimpleDateFormat("d MMMM yyyy", locale)
    return sdf.format(Date(this))
}

fun Long.toFormattedDateTime(): String {
    val locale = Locale("kk", "KZ")
    val sdf = SimpleDateFormat("d MMMM yyyy, HH:mm", locale)
    return sdf.format(Date(this))
}

fun Long.isSameDay(other: Long): Boolean {
    val cal1 = Calendar.getInstance().apply { timeInMillis = this@isSameDay }
    val cal2 = Calendar.getInstance().apply { timeInMillis = other }
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
           cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

fun monthStart(): Long {
    val cal = Calendar.getInstance()
    cal.set(Calendar.DAY_OF_MONTH, 1)
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    return cal.timeInMillis
}

fun monthEnd(): Long {
    val cal = Calendar.getInstance()
    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
    cal.set(Calendar.HOUR_OF_DAY, 23)
    cal.set(Calendar.MINUTE, 59)
    cal.set(Calendar.SECOND, 59)
    cal.set(Calendar.MILLISECOND, 999)
    return cal.timeInMillis
}

fun dayStart(timeMs: Long = System.currentTimeMillis()): Long {
    val cal = Calendar.getInstance().apply { timeInMillis = timeMs }
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    return cal.timeInMillis
}

fun dayEnd(timeMs: Long = System.currentTimeMillis()): Long {
    val cal = Calendar.getInstance().apply { timeInMillis = timeMs }
    cal.set(Calendar.HOUR_OF_DAY, 23)
    cal.set(Calendar.MINUTE, 59)
    cal.set(Calendar.SECOND, 59)
    cal.set(Calendar.MILLISECOND, 999)
    return cal.timeInMillis
}

fun List<Transaction>.groupByDateHeaders(context: Context): List<TransactionItem> {
    if (isEmpty()) return emptyList()
    val todayStart = dayStart()
    val yesterdayStart = todayStart - 86_400_000L
    val dateFmt = SimpleDateFormat("d MMMM", Locale("kk", "KZ"))
    return groupBy { dayStart(it.date) }
        .entries
        .sortedByDescending { it.key }
        .flatMap { (dayMs, txs) ->
            val label = when {
                dayMs >= todayStart -> context.getString(R.string.today)
                dayMs >= yesterdayStart -> context.getString(R.string.yesterday)
                else -> dateFmt.format(Date(dayMs))
            }
            listOf(TransactionItem.Header(label)) + txs.map { TransactionItem.Entry(it) }
        }
}
