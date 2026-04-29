package kz.diploma.securefinance.ui.dashboard;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\u0016\u0010\u001d\u001a\u00020\u001a2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lkz/diploma/securefinance/ui/dashboard/DashboardFragment;", "Landroidx/fragment/app/Fragment;", "()V", "adapter", "Lkz/diploma/securefinance/ui/transactions/TransactionAdapter;", "pieChart", "Lcom/github/mikephil/charting/charts/PieChart;", "rvTransactions", "Landroidx/recyclerview/widget/RecyclerView;", "tvDiff", "Landroid/widget/TextView;", "tvExpense", "tvIncome", "tvNoTransactions", "tvTotalBalance", "viewModel", "Lkz/diploma/securefinance/ui/dashboard/DashboardViewModel;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "updateDiff", "updatePieChart", "stats", "", "Lkz/diploma/securefinance/data/dao/CategoryStat;", "app_debug"})
public final class DashboardFragment extends androidx.fragment.app.Fragment {
    private kz.diploma.securefinance.ui.dashboard.DashboardViewModel viewModel;
    private android.widget.TextView tvTotalBalance;
    private android.widget.TextView tvIncome;
    private android.widget.TextView tvExpense;
    private android.widget.TextView tvDiff;
    private androidx.recyclerview.widget.RecyclerView rvTransactions;
    private com.github.mikephil.charting.charts.PieChart pieChart;
    private android.widget.TextView tvNoTransactions;
    @org.jetbrains.annotations.NotNull()
    private final kz.diploma.securefinance.ui.transactions.TransactionAdapter adapter = null;
    
    public DashboardFragment() {
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
    
    private final void updateDiff() {
    }
    
    private final void updatePieChart(java.util.List<kz.diploma.securefinance.data.dao.CategoryStat> stats) {
    }
}