package kz.diploma.securefinance.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\f"}, d2 = {"Lkz/diploma/securefinance/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "accountDao", "Lkz/diploma/securefinance/data/dao/AccountDao;", "categoryDao", "Lkz/diploma/securefinance/data/dao/CategoryDao;", "exchangeRateDao", "Lkz/diploma/securefinance/data/dao/ExchangeRateDao;", "transactionDao", "Lkz/diploma/securefinance/data/dao/TransactionDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {kz.diploma.securefinance.data.entity.Account.class, kz.diploma.securefinance.data.entity.Category.class, kz.diploma.securefinance.data.entity.Transaction.class, kz.diploma.securefinance.data.entity.ExchangeRate.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String DB_NAME = "securefinance.db";
    @org.jetbrains.annotations.NotNull()
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @org.jetbrains.annotations.NotNull()
    public static final kz.diploma.securefinance.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract kz.diploma.securefinance.data.dao.AccountDao accountDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kz.diploma.securefinance.data.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kz.diploma.securefinance.data.dao.TransactionDao transactionDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract kz.diploma.securefinance.data.dao.ExchangeRateDao exchangeRateDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u00a8\u0006\u000f"}, d2 = {"Lkz/diploma/securefinance/data/AppDatabase$Companion;", "", "()V", "DB_NAME", "", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "getMIGRATION_1_2", "()Landroidx/room/migration/Migration;", "create", "Lkz/diploma/securefinance/data/AppDatabase;", "context", "Landroid/content/Context;", "passphrase", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.room.migration.Migration getMIGRATION_1_2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final kz.diploma.securefinance.data.AppDatabase create(@org.jetbrains.annotations.NotNull()
        android.content.Context context, @org.jetbrains.annotations.NotNull()
        byte[] passphrase) {
            return null;
        }
    }
}