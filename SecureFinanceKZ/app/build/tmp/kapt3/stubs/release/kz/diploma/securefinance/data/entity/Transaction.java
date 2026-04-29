package kz.diploma.securefinance.data.entity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087\b\u0018\u0000 (2\u00020\u0001:\u0001(BE\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010\u001e\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003JV\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010!J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\t\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017\u00a8\u0006)"}, d2 = {"Lkz/diploma/securefinance/data/entity/Transaction;", "", "id", "", "amount", "", "type", "", "accountId", "categoryId", "note", "date", "(JDLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;J)V", "getAccountId", "()J", "getAmount", "()D", "getCategoryId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getDate", "getId", "getNote", "()Ljava/lang/String;", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "(JDLjava/lang/String;JLjava/lang/Long;Ljava/lang/String;J)Lkz/diploma/securefinance/data/entity/Transaction;", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_release"})
@androidx.room.Entity(tableName = "transactions", foreignKeys = {@androidx.room.ForeignKey(entity = kz.diploma.securefinance.data.entity.Account.class, parentColumns = {"id"}, childColumns = {"accountId"}, onDelete = 5), @androidx.room.ForeignKey(entity = kz.diploma.securefinance.data.entity.Category.class, parentColumns = {"id"}, childColumns = {"categoryId"}, onDelete = 3)}, indices = {@androidx.room.Index(value = {"accountId"}), @androidx.room.Index(value = {"categoryId"}), @androidx.room.Index(value = {"date"})})
public final class Transaction {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final double amount = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String type = null;
    private final long accountId = 0L;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Long categoryId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String note = null;
    private final long date = 0L;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE_INCOME = "INCOME";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TYPE_EXPENSE = "EXPENSE";
    @org.jetbrains.annotations.NotNull()
    public static final kz.diploma.securefinance.data.entity.Transaction.Companion Companion = null;
    
    public Transaction(long id, double amount, @org.jetbrains.annotations.NotNull()
    java.lang.String type, long accountId, @org.jetbrains.annotations.Nullable()
    java.lang.Long categoryId, @org.jetbrains.annotations.NotNull()
    java.lang.String note, long date) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final double getAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getType() {
        return null;
    }
    
    public final long getAccountId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getCategoryId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNote() {
        return null;
    }
    
    public final long getDate() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    public final long component4() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    public final long component7() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kz.diploma.securefinance.data.entity.Transaction copy(long id, double amount, @org.jetbrains.annotations.NotNull()
    java.lang.String type, long accountId, @org.jetbrains.annotations.Nullable()
    java.lang.Long categoryId, @org.jetbrains.annotations.NotNull()
    java.lang.String note, long date) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lkz/diploma/securefinance/data/entity/Transaction$Companion;", "", "()V", "TYPE_EXPENSE", "", "TYPE_INCOME", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}