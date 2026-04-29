package kz.diploma.securefinance.ui.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.data.entity.Transaction
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.util.monthEnd
import kz.diploma.securefinance.util.monthStart
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BudgetReceiver : BroadcastReceiver() {

    companion object {
        const val PREF_BUDGET = "budget_monthly_kzt"
    }

    override fun onReceive(context: Context, intent: Intent) {
        val prefs = context.getSharedPreferences("sf_settings", Context.MODE_PRIVATE)
        val budget = prefs.getFloat(PREF_BUDGET, 0f).toDouble()
        if (budget <= 0) return

        if (!SessionManager.isUnlocked) return

        val app = context.applicationContext as FinanceApp
        val repo = app.repository ?: return

        val pendingResult = goAsync()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val from = monthStart(); val to = monthEnd()
                val stats = repo.getCategoryStats(from, to)
                val totalExpense = stats.sumOf { it.total }
                if (totalExpense >= budget) {
                    NotificationHelper.showBudgetAlert(context, totalExpense, budget)
                }
            } finally {
                pendingResult.finish()
            }
        }
    }
}
