package kz.diploma.securefinance.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0014\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0007H\'J\u0018\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lkz/diploma/securefinance/data/dao/ExchangeRateDao;", "", "getAll", "", "Lkz/diploma/securefinance/data/entity/ExchangeRate;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllLive", "Landroidx/lifecycle/LiveData;", "getByCurrency", "currency", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsert", "", "rate", "(Lkz/diploma/securefinance/data/entity/ExchangeRate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertAll", "rates", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ExchangeRateDao {
    
    @androidx.room.Query(value = "SELECT * FROM exchange_rates")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.lifecycle.LiveData<java.util.List<kz.diploma.securefinance.data.entity.ExchangeRate>> getAllLive();
    
    @androidx.room.Query(value = "SELECT * FROM exchange_rates")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kz.diploma.securefinance.data.entity.ExchangeRate>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM exchange_rates WHERE currency = :currency LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getByCurrency(@org.jetbrains.annotations.NotNull()
    java.lang.String currency, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kz.diploma.securefinance.data.entity.ExchangeRate> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    kz.diploma.securefinance.data.entity.ExchangeRate rate, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<kz.diploma.securefinance.data.entity.ExchangeRate> rates, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}