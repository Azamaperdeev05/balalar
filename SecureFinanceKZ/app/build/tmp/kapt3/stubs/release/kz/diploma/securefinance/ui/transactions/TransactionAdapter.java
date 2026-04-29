package kz.diploma.securefinance.ui.transactions;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u0012\u0013\u0014B\u001d\u0012\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0016R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lkz/diploma/securefinance/ui/transactions/TransactionAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lkz/diploma/securefinance/ui/transactions/TransactionItem;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onItemClick", "Lkotlin/Function1;", "Lkz/diploma/securefinance/data/entity/Transaction;", "", "(Lkotlin/jvm/functions/Function1;)V", "getItemViewType", "", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "EntryViewHolder", "HeaderViewHolder", "app_release"})
public final class TransactionAdapter extends androidx.recyclerview.widget.ListAdapter<kz.diploma.securefinance.ui.transactions.TransactionItem, androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    @org.jetbrains.annotations.Nullable()
    private final kotlin.jvm.functions.Function1<kz.diploma.securefinance.data.entity.Transaction, kotlin.Unit> onItemClick = null;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ENTRY = 1;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<kz.diploma.securefinance.ui.transactions.TransactionItem> DIFF = null;
    @org.jetbrains.annotations.NotNull()
    public static final kz.diploma.securefinance.ui.transactions.TransactionAdapter.Companion Companion = null;
    
    public TransactionAdapter(@org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super kz.diploma.securefinance.data.entity.Transaction, kotlin.Unit> onItemClick) {
        super(null);
    }
    
    @java.lang.Override()
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    public TransactionAdapter() {
        super(null);
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lkz/diploma/securefinance/ui/transactions/TransactionAdapter$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lkz/diploma/securefinance/ui/transactions/TransactionItem;", "TYPE_ENTRY", "", "TYPE_HEADER", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\b\u00a8\u0006\u0011"}, d2 = {"Lkz/diploma/securefinance/ui/transactions/TransactionAdapter$EntryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Lkz/diploma/securefinance/ui/transactions/TransactionAdapter;Landroid/view/View;)V", "tvAmount", "Landroid/widget/TextView;", "getTvAmount", "()Landroid/widget/TextView;", "tvDate", "getTvDate", "tvNote", "getTvNote", "bind", "", "entry", "Lkz/diploma/securefinance/ui/transactions/TransactionItem$Entry;", "app_release"})
    public final class EntryViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvAmount = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvNote = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tvDate = null;
        
        public EntryViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View view) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvAmount() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvNote() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTvDate() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        kz.diploma.securefinance.ui.transactions.TransactionItem.Entry entry) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lkz/diploma/securefinance/ui/transactions/TransactionAdapter$HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "tv", "Landroid/widget/TextView;", "(Lkz/diploma/securefinance/ui/transactions/TransactionAdapter;Landroid/widget/TextView;)V", "getTv", "()Landroid/widget/TextView;", "bind", "", "header", "Lkz/diploma/securefinance/ui/transactions/TransactionItem$Header;", "app_release"})
    public final class HeaderViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView tv = null;
        
        public HeaderViewHolder(@org.jetbrains.annotations.NotNull()
        android.widget.TextView tv) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getTv() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        kz.diploma.securefinance.ui.transactions.TransactionItem.Header header) {
        }
    }
}