package kz.diploma.securefinance.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u000bH\'J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u000b2\u0006\u0010\u0011\u001a\u00020\u0012H\'J\u000e\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0018\u001a\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00a2\u0006\u0002\u0010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006\u001c"}, d2 = {"Lkz/diploma/securefinance/data/dao/CategoryDao;", "", "delete", "", "category", "Lkz/diploma/securefinance/data/entity/Category;", "(Lkz/diploma/securefinance/data/entity/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllLive", "Landroidx/lifecycle/LiveData;", "getById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByType", "type", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getByTypeLive", "getCount", "", "insert", "insertAll", "categories", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "app_release"})
@androidx.room.Dao()
public abstract interface CategoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM categories ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Category>> getAllLive();
    
    @androidx.room.Query(value = "SELECT * FROM categories ORDER BY name ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Category>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Category>> getByTypeLive(@org.jetbrains.annotations.NotNull()
    java.lang.String type);
    
    @androidx.room.Query(value = "SELECT * FROM categories WHERE type = :type ORDER BY name ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByType(@org.jetbrains.annotations.NotNull()
    java.lang.String type, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Category>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM categories WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kz.diploma.securefinance.data.entity.Category> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<kz.diploma.securefinance.data.entity.Category> categories, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Category category, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM categories")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}