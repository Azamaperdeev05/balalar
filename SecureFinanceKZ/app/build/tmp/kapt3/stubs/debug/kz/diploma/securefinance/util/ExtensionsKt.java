package kz.diploma.securefinance.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000L\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u001a\u0010\u0010\u0003\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0001\u001a\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0006\u0010\u0005\u001a\u00020\u0001\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\b\u001a\n\u0010\t\u001a\u00020\u0007*\u00020\b\u001a\u0014\u0010\n\u001a\u00020\u000b*\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000b\u001a\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f*\b\u0012\u0004\u0012\u00020\u00110\u000f2\u0006\u0010\u0012\u001a\u00020\u0013\u001a\n\u0010\u0014\u001a\u00020\u0007*\u00020\u0015\u001a\u0012\u0010\u0016\u001a\u00020\u0017*\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0001\u001a\u001c\u0010\u0019\u001a\u00020\u0007*\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u000b2\b\b\u0002\u0010\u001b\u001a\u00020\u001c\u001a\n\u0010\u001d\u001a\u00020\u000b*\u00020\u0001\u001a\n\u0010\u001e\u001a\u00020\u000b*\u00020\u0001\u00a8\u0006\u001f"}, d2 = {"dayEnd", "", "timeMs", "dayStart", "monthEnd", "monthStart", "disableFlagSecure", "", "Landroid/app/Activity;", "enableFlagSecure", "formatMoney", "", "", "currency", "groupByDateHeaders", "", "Lkz/diploma/securefinance/ui/transactions/TransactionItem;", "Lkz/diploma/securefinance/data/entity/Transaction;", "context", "Landroid/content/Context;", "hideKeyboard", "Landroid/view/View;", "isSameDay", "", "other", "showSnackbar", "message", "duration", "", "toFormattedDate", "toFormattedDateTime", "app_debug"})
public final class ExtensionsKt {
    
    public static final void enableFlagSecure(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$enableFlagSecure) {
    }
    
    public static final void disableFlagSecure(@org.jetbrains.annotations.NotNull()
    android.app.Activity $this$disableFlagSecure) {
    }
    
    public static final void hideKeyboard(@org.jetbrains.annotations.NotNull()
    android.view.View $this$hideKeyboard) {
    }
    
    public static final void showSnackbar(@org.jetbrains.annotations.NotNull()
    android.view.View $this$showSnackbar, @org.jetbrains.annotations.NotNull()
    java.lang.String message, int duration) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String formatMoney(double $this$formatMoney, @org.jetbrains.annotations.NotNull()
    java.lang.String currency) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toFormattedDate(long $this$toFormattedDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String toFormattedDateTime(long $this$toFormattedDateTime) {
        return null;
    }
    
    public static final boolean isSameDay(long $this$isSameDay, long other) {
        return false;
    }
    
    public static final long monthStart() {
        return 0L;
    }
    
    public static final long monthEnd() {
        return 0L;
    }
    
    public static final long dayStart(long timeMs) {
        return 0L;
    }
    
    public static final long dayEnd(long timeMs) {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.util.List<kz.diploma.securefinance.ui.transactions.TransactionItem> groupByDateHeaders(@org.jetbrains.annotations.NotNull()
    java.util.List<kz.diploma.securefinance.data.entity.Transaction> $this$groupByDateHeaders, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
}