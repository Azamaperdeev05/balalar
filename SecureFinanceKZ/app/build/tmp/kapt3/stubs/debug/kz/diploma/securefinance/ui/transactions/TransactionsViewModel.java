package kz.diploma.securefinance.ui.transactions;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0013J\u0006\u0010\u001b\u001a\u00020\u0016R\u001c\u0010\u0005\u001a\u0010\u0012\f\u0012\n \b*\u0004\u0018\u00010\u00070\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010\u00a8\u0006\u001c"}, d2 = {"Lkz/diploma/securefinance/ui/transactions/TransactionsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lkz/diploma/securefinance/data/FinanceRepository;", "(Lkz/diploma/securefinance/data/FinanceRepository;)V", "_filter", "Landroidx/lifecycle/MutableLiveData;", "Lkz/diploma/securefinance/ui/transactions/TransactionFilter;", "kotlin.jvm.PlatformType", "_loadError", "", "exceptionHandler", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "loadError", "Landroidx/lifecycle/LiveData;", "getLoadError", "()Landroidx/lifecycle/LiveData;", "transactions", "", "Lkz/diploma/securefinance/data/entity/Transaction;", "getTransactions", "applyFilter", "", "filter", "deleteTransaction", "Lkotlinx/coroutines/Job;", "tx", "resetFilter", "app_debug"})
public final class TransactionsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kz.diploma.securefinance.data.FinanceRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _loadError = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> loadError = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineExceptionHandler exceptionHandler = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<kz.diploma.securefinance.ui.transactions.TransactionFilter> _filter = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> transactions = null;
    
    public TransactionsViewModel(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.FinanceRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getLoadError() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> getTransactions() {
        return null;
    }
    
    public final void applyFilter(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.ui.transactions.TransactionFilter filter) {
    }
    
    public final void resetFilter() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteTransaction(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Transaction tx) {
        return null;
    }
}