package kz.diploma.securefinance.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0014\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J8\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u00152\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015H\u0086@\u00a2\u0006\u0002\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u0016\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u001c\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0086@\u00a2\u0006\u0002\u0010 J\u0012\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00150\"J\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020\f0\u0015H\u0086@\u00a2\u0006\u0002\u0010 J\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00150\"J\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00100\u0015H\u0086@\u00a2\u0006\u0002\u0010 J\u0012\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00150\"J\u001c\u0010\'\u001a\b\u0012\u0004\u0012\u00020\f0\u00152\u0006\u0010(\u001a\u00020)H\u0086@\u00a2\u0006\u0002\u0010*J\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00150\"2\u0006\u0010(\u001a\u00020)J\u000e\u0010,\u001a\u00020-H\u0086@\u00a2\u0006\u0002\u0010 J$\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00152\u0006\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u00102J\"\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\u00150\"2\u0006\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006J\u0014\u00104\u001a\b\u0012\u0004\u0012\u0002050\u0015H\u0086@\u00a2\u0006\u0002\u0010 J\u0012\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u00150\"J_\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00150\"2\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010(\u001a\u0004\u0018\u00010)2\n\b\u0002\u00108\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u00109\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010:\u001a\u0004\u0018\u00010)\u00a2\u0006\u0002\u0010;J$\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\"2\u0006\u0010(\u001a\u00020)2\u0006\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u0006J\u001c\u0010>\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00150\"2\b\b\u0002\u0010?\u001a\u00020-J\u000e\u0010@\u001a\u00020=H\u0086@\u00a2\u0006\u0002\u0010 J\f\u0010A\u001a\b\u0012\u0004\u0012\u00020=0\"J\u0018\u0010B\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001d\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u0010\u001eJ$\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00100\u00152\u0006\u0010D\u001a\u00020\u00062\u0006\u0010E\u001a\u00020\u0006H\u0086@\u00a2\u0006\u0002\u00102J\u001c\u0010F\u001a\u00020\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u0015H\u0086@\u00a2\u0006\u0002\u0010GJ\u0016\u0010H\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010I\u001a\u00020\u00132\u0006\u0010\u000b\u001a\u00020\fH\u0086@\u00a2\u0006\u0002\u0010\rJ\u001e\u0010J\u001a\u00020\u00132\u0006\u0010K\u001a\u00020\u00102\u0006\u0010L\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010MJ\u0016\u0010N\u001a\u00020\u00132\u0006\u0010O\u001a\u000205H\u0086@\u00a2\u0006\u0002\u0010PR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006Q"}, d2 = {"Lkz/diploma/securefinance/data/FinanceRepository;", "", "db", "Lkz/diploma/securefinance/data/AppDatabase;", "(Lkz/diploma/securefinance/data/AppDatabase;)V", "addAccount", "", "account", "Lkz/diploma/securefinance/data/entity/Account;", "(Lkz/diploma/securefinance/data/entity/Account;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addCategory", "category", "Lkz/diploma/securefinance/data/entity/Category;", "(Lkz/diploma/securefinance/data/entity/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addTransaction", "transaction", "Lkz/diploma/securefinance/data/entity/Transaction;", "(Lkz/diploma/securefinance/data/entity/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearAndRestore", "", "accounts", "", "categories", "transactions", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAccount", "deleteCategory", "deleteTransaction", "getAccountById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAccounts", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllAccountsLive", "Landroidx/lifecycle/LiveData;", "getAllCategories", "getAllCategoriesLive", "getAllTransactions", "getAllTransactionsLive", "getCategoriesByType", "type", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoriesByTypeLive", "getCategoryCount", "", "getCategoryStats", "Lkz/diploma/securefinance/data/dao/CategoryStat;", "fromDate", "toDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoryStatsLive", "getExchangeRates", "Lkz/diploma/securefinance/data/entity/ExchangeRate;", "getExchangeRatesLive", "getFilteredTransactionsLive", "accountId", "categoryId", "query", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Landroidx/lifecycle/LiveData;", "getMonthSumLive", "", "getRecentTransactionsLive", "limit", "getTotalBalance", "getTotalBalanceLive", "getTransactionById", "getTransactionsByDateRange", "from", "to", "insertDefaultCategories", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAccount", "updateCategory", "updateTransaction", "old", "new", "(Lkz/diploma/securefinance/data/entity/Transaction;Lkz/diploma/securefinance/data/entity/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertExchangeRate", "rate", "(Lkz/diploma/securefinance/data/entity/ExchangeRate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class FinanceRepository {
    @org.jetbrains.annotations.NotNull()
    private final kz.diploma.securefinance.data.AppDatabase db = null;
    
    public FinanceRepository(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.AppDatabase db) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Account>> getAllAccountsLive() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllAccounts(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Account>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalBalanceLive() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addAccount(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateAccount(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteAccount(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAccountById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kz.diploma.securefinance.data.entity.Account> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Category>> getAllCategoriesLive() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllCategories(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Category>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Category>> getCategoriesByTypeLive(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCategoriesByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Category>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addCategory(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateCategory(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteCategory(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCategoryCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> getAllTransactionsLive() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> getFilteredTransactionsLive(@org.jetbrains.annotations.Nullable()
    java.lang.Long fromDate, @org.jetbrains.annotations.Nullable()
    java.lang.Long toDate, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.Long accountId, @org.jetbrains.annotations.Nullable()
    java.lang.Long categoryId, @org.jetbrains.annotations.Nullable()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> getRecentTransactionsLive(int limit) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getMonthSumLive(@org.jetbrains.annotations.NotNull()
    java.lang.String type, long fromDate, long toDate) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.dao.CategoryStat>> getCategoryStatsLive(long fromDate, long toDate) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCategoryStats(long fromDate, long toDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.dao.CategoryStat>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionsByDateRange(long from, long to, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Transaction>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addTransaction(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateTransaction(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Transaction old, @org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Transaction p1_54480, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteTransaction(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTransactionById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kz.diploma.securefinance.data.entity.Transaction> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllTransactions(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Transaction>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearAndRestore(@org.jetbrains.annotations.NotNull()
    java.util.List<kz.diploma.securefinance.data.entity.Account> accounts, @org.jetbrains.annotations.NotNull()
    java.util.List<kz.diploma.securefinance.data.entity.Category> categories, @org.jetbrains.annotations.NotNull()
    java.util.List<kz.diploma.securefinance.data.entity.Transaction> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertDefaultCategories(@org.jetbrains.annotations.NotNull()
    java.util.List<kz.diploma.securefinance.data.entity.Category> categories, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.ExchangeRate>> getExchangeRatesLive() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getExchangeRates(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.ExchangeRate>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object upsertExchangeRate(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.ExchangeRate rate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getTotalBalance(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
}