package kz.diploma.securefinance.ui.categories;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lkz/diploma/securefinance/ui/categories/CategoriesViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lkz/diploma/securefinance/data/FinanceRepository;", "(Lkz/diploma/securefinance/data/FinanceRepository;)V", "addCategory", "Lkotlinx/coroutines/Job;", "category", "Lkz/diploma/securefinance/data/entity/Category;", "deleteCategory", "getCategoriesByType", "Landroidx/lifecycle/LiveData;", "", "type", "", "updateCategory", "app_debug"})
public final class CategoriesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final kz.diploma.securefinance.data.FinanceRepository repository = null;
    
    public CategoriesViewModel(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.FinanceRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Category>> getCategoriesByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job addCategory(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateCategory(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteCategory(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category) {
        return null;
    }
}