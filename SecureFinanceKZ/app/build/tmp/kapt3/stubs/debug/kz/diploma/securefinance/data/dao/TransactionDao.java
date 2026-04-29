package kz.diploma.securefinance.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\fH\'J$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0013\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0014J$\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0011J$\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\n0\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\'Ja\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\f2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001aH\'\u00a2\u0006\u0002\u0010\u001eJ\u001e\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\n0\f2\b\b\u0002\u0010 \u001a\u00020!H\'J&\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\'J\u0016\u0010$\u001a\u00020\u000f2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010%\u001a\u00020\u00032\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u00a7@\u00a2\u0006\u0002\u0010\'J\u0016\u0010(\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006\u00a8\u0006)"}, d2 = {"Lkz/diploma/securefinance/data/dao/TransactionDao;", "", "delete", "", "transaction", "Lkz/diploma/securefinance/data/entity/Transaction;", "(Lkz/diploma/securefinance/data/entity/Transaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "getAllLive", "Landroidx/lifecycle/LiveData;", "getByDateRange", "fromDate", "", "toDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCategoryStats", "Lkz/diploma/securefinance/data/dao/CategoryStat;", "getCategoryStatsLive", "getFilteredLive", "type", "", "accountId", "categoryId", "query", "(Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;)Landroidx/lifecycle/LiveData;", "getRecentLive", "limit", "", "getSumByTypeLive", "", "insert", "insertAll", "transactions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "app_debug"})
@androidx.room.Dao()
public abstract interface TransactionDao {
    
    @androidx.room.Query(value = "SELECT * FROM transactions ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> getAllLive();
    
    @androidx.room.Query(value = "SELECT * FROM transactions ORDER BY date DESC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Transaction>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transactions WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kz.diploma.securefinance.data.entity.Transaction> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM transactions\n        WHERE (:fromDate IS NULL OR date >= :fromDate)\n          AND (:toDate IS NULL OR date <= :toDate)\n          AND (:type IS NULL OR type = :type)\n          AND (:accountId IS NULL OR accountId = :accountId)\n          AND (:categoryId IS NULL OR categoryId = :categoryId)\n          AND (:query IS NULL OR note LIKE \'%\' || :query || \'%\')\n        ORDER BY date DESC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> getFilteredLive(@org.jetbrains.annotations.Nullable()
    java.lang.Long fromDate, @org.jetbrains.annotations.Nullable()
    java.lang.Long toDate, @org.jetbrains.annotations.Nullable()
    java.lang.String type, @org.jetbrains.annotations.Nullable()
    java.lang.Long accountId, @org.jetbrains.annotations.Nullable()
    java.lang.Long categoryId, @org.jetbrains.annotations.Nullable()
    java.lang.String query);
    
    @androidx.room.Query(value = "SELECT * FROM transactions ORDER BY date DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Transaction>> getRecentLive(int limit);
    
    @androidx.room.Query(value = "\n        SELECT IFNULL(SUM(amount), 0.0) FROM transactions\n        WHERE type = :type AND date >= :fromDate AND date <= :toDate\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getSumByTypeLive(@org.jetbrains.annotations.NotNull()
    java.lang.String type, long fromDate, long toDate);
    
    @androidx.room.Query(value = "\n        SELECT t.categoryId, c.name AS categoryName, SUM(t.amount) AS total, c.color\n        FROM transactions t\n        LEFT JOIN categories c ON t.categoryId = c.id\n        WHERE t.type = \'EXPENSE\'\n          AND t.date >= :fromDate AND t.date <= :toDate\n        GROUP BY t.categoryId\n        ORDER BY total DESC\n    ")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.dao.CategoryStat>> getCategoryStatsLive(long fromDate, long toDate);
    
    @androidx.room.Query(value = "\n        SELECT t.categoryId, c.name AS categoryName, SUM(t.amount) AS total, c.color\n        FROM transactions t\n        LEFT JOIN categories c ON t.categoryId = c.id\n        WHERE t.type = \'EXPENSE\'\n          AND t.date >= :fromDate AND t.date <= :toDate\n        GROUP BY t.categoryId\n        ORDER BY total DESC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCategoryStats(long fromDate, long toDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.dao.CategoryStat>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM transactions\n        WHERE date >= :fromDate AND date <= :toDate\n        ORDER BY date ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByDateRange(long fromDate, long toDate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Transaction>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<kz.diploma.securefinance.data.entity.Transaction> transactions, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Transaction transaction, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM transactions")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}