package kz.diploma.securefinance.ui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import kz.diploma.securefinance.R

object NotificationHelper {

    const val CHANNEL_BUDGET = "budget_alert"
    const val NOTIF_BUDGET_ID = 1001

    fun createChannels(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_BUDGET,
                context.getString(R.string.notif_channel_budget),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = context.getString(R.string.notif_channel_budget_desc)
            }
            context.getSystemService(NotificationManager::class.java)
                ?.createNotificationChannel(channel)
        }
    }

    fun showBudgetAlert(context: Context, spent: Double, budget: Double) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(context, CHANNEL_BUDGET)
            .setSmallIcon(R.drawable.ic_empty_transactions)
            .setContentTitle(context.getString(R.string.notif_budget_title))
            .setContentText(context.getString(R.string.notif_budget_msg,
                spent.toLong(), budget.toLong()))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()
        manager.notify(NOTIF_BUDGET_ID, notification)
    }
}
