package kz.diploma.securefinance.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u001e\u001a\u00020\u001fR4\u0010\u0005\u001a(\u0012$\u0012\"\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b \t*\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u001d\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011\u00a8\u0006 "}, d2 = {"Lkz/diploma/securefinance/ui/dashboard/DashboardViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lkz/diploma/securefinance/data/FinanceRepository;", "(Lkz/diploma/securefinance/data/FinanceRepository;)V", "_monthRange", "Landroidx/lifecycle/MutableLiveData;", "Lkotlin/Pair;", "", "kotlin.jvm.PlatformType", "accounts", "Landroidx/lifecycle/LiveData;", "", "Lkz/diploma/securefinance/data/entity/Account;", "categoryStats", "Lkz/diploma/securefinance/data/dao/CategoryStat;", "getCategoryStats", "()Landroidx/lifecycle/LiveData;", "exchangeRates", "Lkz/diploma/securefinance/data/entity/ExchangeRate;", "monthExpense", "", "getMonthExpense", "monthIncome", "getMonthIncome", "recentTransactions", "Lkz/diploma/securefinance/data/entity/Transaction;", "getRecentTransactions", "totalBalance", "getTotalBalance", "refreshMonth", "", "app_debug"})
public final class DashboardViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kz.diploma.securefinance.data.FinanceRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Account>> accounts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.ExchangeRate>> exchangeRates = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalBalance = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> recentTransactions = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.Long, java.lang.Long>> _monthRange = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> monthIncome = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> monthExpense = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.dao.CategoryStat>> categoryStats = null;
    
    public DashboardViewModel(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.FinanceRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> getRecentTransactions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getMonthIncome() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getMonthExpense() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.dao.CategoryStat>> getCategoryStats() {
        return null;
    }
    
    public final void refreshMonth() {
    }
}