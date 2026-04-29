package kz.diploma.securefinance.ui.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import kz.diploma.securefinance.FinanceApp
import kz.diploma.securefinance.R
import kz.diploma.securefinance.security.SessionManager
import kz.diploma.securefinance.util.formatMoney
import kotlinx.coroutines.runBlocking

class BalanceWidget : AppWidgetProvider() {

    override fun onUpdate(context: Context, manager: AppWidgetManager, appWidgetIds: IntArray) {
        appWidgetIds.forEach { id ->
            updateWidget(context, manager, id)
        }
    }

    companion object {
        fun updateWidget(context: Context, manager: AppWidgetManager, id: Int) {
            val views = RemoteViews(context.packageName, R.layout.widget_balance)
            val balance = if (SessionManager.isUnlocked) {
                val app = context.applicationContext as FinanceApp
                try {
                    val total = runBlocking { app.repository?.getTotalBalance() ?: 0.0 }
                    total.formatMoney()
                } catch (e: Exception) {
                    "—"
                }
            } else {
                context.getString(R.string.widget_locked)
            }
            views.setTextViewText(R.id.tvWidgetBalance, balance)
            manager.updateAppWidget(id, views)
        }
    }
}
