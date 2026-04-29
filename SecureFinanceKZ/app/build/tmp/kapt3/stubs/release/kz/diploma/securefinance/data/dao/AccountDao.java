package kz.diploma.securefinance.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000e0\u0011H\'J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0013\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u000e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0011H\'J\u0016\u0010\u0017\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u001e\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0019\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\u001a\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u001b"}, d2 = {"Lkz/diploma/securefinance/data/dao/AccountDao;", "", "adjustBalance", "", "accountId", "", "delta", "", "(JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "account", "Lkz/diploma/securefinance/data/entity/Account;", "(Lkz/diploma/securefinance/data/entity/Account;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllLive", "Landroidx/lifecycle/LiveData;", "getById", "id", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalBalance", "getTotalBalanceLive", "insert", "setBalance", "newBalance", "update", "app_release"})
@androidx.room.Dao()
public abstract interface AccountDao {
    
    @androidx.room.Query(value = "SELECT * FROM accounts ORDER BY createdAt ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.Account>> getAllLive();
    
    @androidx.room.Query(value = "SELECT * FROM accounts ORDER BY createdAt ASC")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.Account>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM accounts WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kz.diploma.securefinance.data.entity.Account> $completion);
    
    @androidx.room.Query(value = "SELECT IFNULL(SUM(balance), 0.0) FROM accounts")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTotalBalanceLive();
    
    @androidx.room.Query(value = "SELECT IFNULL(SUM(balance), 0.0) FROM accounts")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getTotalBalance(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.Account account, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE accounts SET balance = balance + :delta WHERE id = :accountId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object adjustBalance(long accountId, double delta, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE accounts SET balance = :newBalance WHERE id = :accountId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object setBalance(long accountId, double newBalance, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}