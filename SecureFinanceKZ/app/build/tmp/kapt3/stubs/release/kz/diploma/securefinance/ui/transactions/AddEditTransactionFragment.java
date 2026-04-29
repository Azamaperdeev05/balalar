package kz.diploma.securefinance.ui.transactions;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0010\u0010$\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010%\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0017H\u0002J&\u0010\'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020*2\b\u0010+\u001a\u0004\u0018\u00010,2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020 H\u0016J\u001a\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020(2\b\u0010-\u001a\u0004\u0018\u00010.H\u0016J\u001e\u00102\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u00103\u001a\u00020\u0010H\u0082@\u00a2\u0006\u0002\u00104J\u0010\u00105\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u00106\u001a\u00020 H\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lkz/diploma/securefinance/ui/transactions/AddEditTransactionFragment;", "Landroidx/fragment/app/Fragment;", "()V", "accounts", "", "Lkz/diploma/securefinance/data/entity/Account;", "amountWatcher", "Landroid/text/TextWatcher;", "btnDelete", "Landroid/widget/Button;", "btnSave", "categories", "Lkz/diploma/securefinance/data/entity/Category;", "currentType", "", "editingTransaction", "Lkz/diploma/securefinance/data/entity/Transaction;", "etAmount", "Lcom/google/android/material/textfield/TextInputEditText;", "etNote", "isUpdating", "", "selectedDate", "", "spinnerAccount", "Landroid/widget/Spinner;", "spinnerCategory", "toggleType", "Lcom/google/android/material/button/MaterialButtonToggleGroup;", "tvDate", "Landroid/widget/TextView;", "confirmDelete", "", "repo", "Lkz/diploma/securefinance/data/FinanceRepository;", "loadAccounts", "loadCategories", "loadExistingTransaction", "id", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "performSave", "tx", "(Lkz/diploma/securefinance/data/FinanceRepository;Lkz/diploma/securefinance/data/entity/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveTransaction", "showDatePicker", "app_release"})
public final class AddEditTransactionFragment extends androidx.fragment.app.Fragment {
    private com.google.android.material.button.MaterialButtonToggleGroup toggleType;
    private com.google.android.material.textfield.TextInputEditText etAmount;
    private android.widget.Spinner spinnerAccount;
    private android.widget.Spinner spinnerCategory;
    private android.widget.TextView tvDate;
    private com.google.android.material.textfield.TextInputEditText etNote;
    private android.widget.Button btnSave;
    private android.widget.Button btnDelete;
    private long selectedDate;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<kz.diploma.securefinance.data.entity.Account> accounts;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<kz.diploma.securefinance.data.entity.Category> categories;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentType = "EXPENSE";
    @org.jetbrains.annotations.Nullable()
    private kz.diploma.securefinance.data.entity.Transaction editingTransaction;
    private boolean isUpdating = false;
    @org.jetbrains.annotations.NotNull()
    private final android.text.TextWatcher amountWatcher = null;
    
    public AddEditTransactionFragment() {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    private final void loadAccounts(kz.diploma.securefinance.data.FinanceRepository repo) {
    }
    
    private final void loadCategories(kz.diploma.securefinance.data.FinanceRepository repo) {
    }
    
    private final void loadExistingTransaction(kz.diploma.securefinance.data.FinanceRepository repo, long id) {
    }
    
    private final void showDatePicker() {
    }
    
    private final void saveTransaction(kz.diploma.securefinance.data.FinanceRepository repo) {
    }
    
    private final java.lang.Object performSave(kz.diploma.securefinance.data.FinanceRepository repo, kz.diploma.securefinance.data.entity.Transaction tx, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void confirmDelete(kz.diploma.securefinance.data.FinanceRepository repo) {
    }
}