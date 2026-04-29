package kz.diploma.securefinance.ui.accounts;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00102\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0010\u0011B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lkz/diploma/securefinance/ui/accounts/AccountAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lkz/diploma/securefinance/data/entity/Account;", "Lkz/diploma/securefinance/ui/accounts/AccountAdapter$ViewHolder;", "onItemClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "ViewHolder", "app_release"})
public final class AccountAdapter extends androidx.recyclerview.widget.ListAdapter<kz.diploma.securefinance.data.entity.Account, kz.diploma.securefinance.ui.accounts.AccountAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<kz.diploma.securefinance.data.entity.Account, kotlin.Unit> onItemClick = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<kz.diploma.securefinance.data.entity.Account> DIFF = null;
    @org.jetbrains.annotations.NotNull()
    public static final kz.diploma.securefinance.ui.accounts.AccountAdapter.Companion Companion = null;
    
    public AccountAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super kz.diploma.securefinance.data.entity.Account, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kz.diploma.securefinance.ui.accounts.AccountAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.ui.accounts.AccountAdapter.ViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lkz/diploma/securefinance/ui/accounts/AccountAdapter$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lkz/diploma/securefinance/data/entity/Account;", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b\u00a8\u0006\u0011"}, d2 = {"Lkz/diploma/securefinance/ui/accounts/AccountAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lkz/diploma/securefinance/ui/accounts/AccountAdapter;Landroid/view/View;)V", "tvBalance", "Landroid/widget/TextView;", "getTvBalance", "()Landroid/widget/TextView;", "tvName", "getTvName", "tvType", "getTvType", "bind", "", "acc", "Lkz/diploma/securefinance/data/entity/Account;", "app_release"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvBalance = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvType = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvBalance() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvType() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        kz.diploma.securefinance.data.entity.Account acc) {
        }
    }
}