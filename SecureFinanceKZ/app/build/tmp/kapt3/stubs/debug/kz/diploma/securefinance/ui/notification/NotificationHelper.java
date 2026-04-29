package kz.diploma.securefinance.ui.notification;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lkz/diploma/securefinance/ui/notification/NotificationHelper;", "", "()V", "CHANNEL_BUDGET", "", "NOTIF_BUDGET_ID", "", "createChannels", "", "context", "Landroid/content/Context;", "showBudgetAlert", "spent", "", "budget", "app_debug"})
public final class NotificationHelper {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_BUDGET = "budget_alert";
    public static final int NOTIF_BUDGET_ID = 1001;
    @org.jetbrains.annotations.NotNull()
    public static final kz.diploma.securefinance.ui.notification.NotificationHelper INSTANCE = null;
    
    private NotificationHelper() {
        super();
    }
    
    public final void createChannels(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
    
    public final void showBudgetAlert(@org.jetbrains.annotations.NotNull()
    android.content.Context context, double spent, double budget) {
    }
}