package kz.diploma.securefinance.ui.statistics;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000bR \u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lkz/diploma/securefinance/ui/statistics/StatisticsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lkz/diploma/securefinance/data/FinanceRepository;", "(Lkz/diploma/securefinance/data/FinanceRepository;)V", "_dateRange", "Landroidx/lifecycle/LiveData;", "Lkotlin/Pair;", "", "_period", "Landroidx/lifecycle/MutableLiveData;", "Lkz/diploma/securefinance/ui/statistics/StatsPeriod;", "kotlin.jvm.PlatformType", "categoryStats", "", "Lkz/diploma/securefinance/data/dao/CategoryStat;", "getCategoryStats", "()Landroidx/lifecycle/LiveData;", "expense", "", "getExpense", "income", "getIncome", "setPeriod", "", "period", "app_release"})
public final class StatisticsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kz.diploma.securefinance.data.FinanceRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<kz.diploma.securefinance.ui.statistics.StatsPeriod> _period = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<kotlin.Pair<java.lang.Long, java.lang.Long>> _dateRange = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> income = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> expense = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.dao.CategoryStat>> categoryStats = null;
    
    public StatisticsViewModel(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.FinanceRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getIncome() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getExpense() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.dao.CategoryStat>> getCategoryStats() {
        return null;
    }
    
    public final void setPeriod(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.ui.statistics.StatsPeriod period) {
    }
}