package kz.diploma.securefinance.ui.accounts;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bJ\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bR\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n\u00a8\u0006\u0013"}, d2 = {"Lkz/diploma/securefinance/ui/accounts/AccountsViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lkz/diploma/securefinance/data/FinanceRepository;", "(Lkz/diploma/securefinance/data/FinanceRepository;)V", "accounts", "Landroidx/lifecycle/LiveData;", "", "Lkz/diploma/securefinance/data/entity/Account;", "getAccounts", "()Landroidx/lifecycle/LiveData;", "totalBalance", "", "getTotalBalance", "addAccount", "Lkotlinx/coroutines/Job;", "account", "deleteAccount", "updateAccount", "app_debug"})
public final class AccountsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kz.diploma.securefinance.data.FinanceRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Account>> accounts = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Double> totalBalance = null;
    
    public AccountsViewModel(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.FinanceRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Account>> getAccounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteAccount(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job addAccount(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateAccount(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account) {
        return null;
    }
}