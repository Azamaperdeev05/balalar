package kz.diploma.securefinance.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kz.diploma.securefinance.data.entity.Transaction;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionDao_Impl implements TransactionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Transaction> __deletionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Transaction> __updateAdapterOfTransaction;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public TransactionDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transactions` (`id`,`amount`,`type`,`accountId`,`categoryId`,`note`,`date`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        if (entity.getType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getType());
        }
        statement.bindLong(4, entity.getAccountId());
        if (entity.getCategoryId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getCategoryId());
        }
        if (entity.getNote() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNote());
        }
        statement.bindLong(7, entity.getDate());
      }
    };
    this.__deletionAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTransaction = new EntityDeletionOrUpdateAdapter<Transaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transactions` SET `id` = ?,`amount` = ?,`type` = ?,`accountId` = ?,`categoryId` = ?,`note` = ?,`date` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Transaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        if (entity.getType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getType());
        }
        statement.bindLong(4, entity.getAccountId());
        if (entity.getCategoryId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindLong(5, entity.getCategoryId());
        }
        if (entity.getNote() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getNote());
        }
        statement.bindLong(7, entity.getDate());
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transactions";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Transaction transaction, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTransaction.insertAndReturnId(transaction);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertAll(final List<Transaction> transactions,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransaction.insert(transactions);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object delete(final Transaction transaction, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object update(final Transaction transaction, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public LiveData<List<Transaction>> getAllLive() {
    final String _sql = "SELECT * FROM transactions ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _item = new Transaction(_tmpId,_tmpAmount,_tmpType,_tmpAccountId,_tmpCategoryId,_tmpNote,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAll(final Continuation<? super List<Transaction>> arg0) {
    final String _sql = "SELECT * FROM transactions ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _item = new Transaction(_tmpId,_tmpAmount,_tmpType,_tmpAccountId,_tmpCategoryId,_tmpNote,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @Override
  public Object getById(final long id, final Continuation<? super Transaction> arg1) {
    final String _sql = "SELECT * FROM transactions WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Transaction>() {
      @Override
      @Nullable
      public Transaction call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final Transaction _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _result = new Transaction(_tmpId,_tmpAmount,_tmpType,_tmpAccountId,_tmpCategoryId,_tmpNote,_tmpDate);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public LiveData<List<Transaction>> getFilteredLive(final Long fromDate, final Long toDate,
      final String type, final Long accountId, final Long categoryId, final String query) {
    final String _sql = "\n"
            + "        SELECT * FROM transactions\n"
            + "        WHERE (? IS NULL OR date >= ?)\n"
            + "          AND (? IS NULL OR date <= ?)\n"
            + "          AND (? IS NULL OR type = ?)\n"
            + "          AND (? IS NULL OR accountId = ?)\n"
            + "          AND (? IS NULL OR categoryId = ?)\n"
            + "          AND (? IS NULL OR note LIKE '%' || ? || '%')\n"
            + "        ORDER BY date DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 12);
    int _argIndex = 1;
    if (fromDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, fromDate);
    }
    _argIndex = 2;
    if (fromDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, fromDate);
    }
    _argIndex = 3;
    if (toDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, toDate);
    }
    _argIndex = 4;
    if (toDate == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, toDate);
    }
    _argIndex = 5;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    _argIndex = 6;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    _argIndex = 7;
    if (accountId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, accountId);
    }
    _argIndex = 8;
    if (accountId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, accountId);
    }
    _argIndex = 9;
    if (categoryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, categoryId);
    }
    _argIndex = 10;
    if (categoryId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindLong(_argIndex, categoryId);
    }
    _argIndex = 11;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 12;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _item = new Transaction(_tmpId,_tmpAmount,_tmpType,_tmpAccountId,_tmpCategoryId,_tmpNote,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Transaction>> getRecentLive(final int limit) {
    final String _sql = "SELECT * FROM transactions ORDER BY date DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<List<Transaction>>() {
      @Override
      @Nullable
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _item = new Transaction(_tmpId,_tmpAmount,_tmpType,_tmpAccountId,_tmpCategoryId,_tmpNote,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getSumByTypeLive(final String type, final long fromDate,
      final long toDate) {
    final String _sql = "\n"
            + "        SELECT IFNULL(SUM(amount), 0.0) FROM transactions\n"
            + "        WHERE type = ? AND date >= ? AND date <= ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, fromDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, toDate);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions"}, false, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<CategoryStat>> getCategoryStatsLive(final long fromDate, final long toDate) {
    final String _sql = "\n"
            + "        SELECT t.categoryId, c.name AS categoryName, SUM(t.amount) AS total, c.color\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.categoryId = c.id\n"
            + "        WHERE t.type = 'EXPENSE'\n"
            + "          AND t.date >= ? AND t.date <= ?\n"
            + "        GROUP BY t.categoryId\n"
            + "        ORDER BY total DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toDate);
    return __db.getInvalidationTracker().createLiveData(new String[] {"transactions",
        "categories"}, false, new Callable<List<CategoryStat>>() {
      @Override
      @Nullable
      public List<CategoryStat> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategoryId = 0;
          final int _cursorIndexOfCategoryName = 1;
          final int _cursorIndexOfTotal = 2;
          final int _cursorIndexOfColor = 3;
          final List<CategoryStat> _result = new ArrayList<CategoryStat>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryStat _item;
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final double _tmpTotal;
            _tmpTotal = _cursor.getDouble(_cursorIndexOfTotal);
            final Integer _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            }
            _item = new CategoryStat(_tmpCategoryId,_tmpCategoryName,_tmpTotal,_tmpColor);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getCategoryStats(final long fromDate, final long toDate,
      final Continuation<? super List<CategoryStat>> arg2) {
    final String _sql = "\n"
            + "        SELECT t.categoryId, c.name AS categoryName, SUM(t.amount) AS total, c.color\n"
            + "        FROM transactions t\n"
            + "        LEFT JOIN categories c ON t.categoryId = c.id\n"
            + "        WHERE t.type = 'EXPENSE'\n"
            + "          AND t.date >= ? AND t.date <= ?\n"
            + "        GROUP BY t.categoryId\n"
            + "        ORDER BY total DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CategoryStat>>() {
      @Override
      @NonNull
      public List<CategoryStat> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategoryId = 0;
          final int _cursorIndexOfCategoryName = 1;
          final int _cursorIndexOfTotal = 2;
          final int _cursorIndexOfColor = 3;
          final List<CategoryStat> _result = new ArrayList<CategoryStat>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryStat _item;
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpCategoryName;
            if (_cursor.isNull(_cursorIndexOfCategoryName)) {
              _tmpCategoryName = null;
            } else {
              _tmpCategoryName = _cursor.getString(_cursorIndexOfCategoryName);
            }
            final double _tmpTotal;
            _tmpTotal = _cursor.getDouble(_cursorIndexOfTotal);
            final Integer _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            }
            _item = new CategoryStat(_tmpCategoryId,_tmpCategoryName,_tmpTotal,_tmpColor);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg2);
  }

  @Override
  public Object getByDateRange(final long fromDate, final long toDate,
      final Continuation<? super List<Transaction>> arg2) {
    final String _sql = "\n"
            + "        SELECT * FROM transactions\n"
            + "        WHERE date >= ? AND date <= ?\n"
            + "        ORDER BY date ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, fromDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, toDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Transaction>>() {
      @Override
      @NonNull
      public List<Transaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "accountId");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfNote = CursorUtil.getColumnIndexOrThrow(_cursor, "note");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Transaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpAccountId;
            _tmpAccountId = _cursor.getLong(_cursorIndexOfAccountId);
            final Long _tmpCategoryId;
            if (_cursor.isNull(_cursorIndexOfCategoryId)) {
              _tmpCategoryId = null;
            } else {
              _tmpCategoryId = _cursor.getLong(_cursorIndexOfCategoryId);
            }
            final String _tmpNote;
            if (_cursor.isNull(_cursorIndexOfNote)) {
              _tmpNote = null;
            } else {
              _tmpNote = _cursor.getString(_cursorIndexOfNote);
            }
            final long _tmpDate;
            _tmpDate = _cursor.getLong(_cursorIndexOfDate);
            _item = new Transaction(_tmpId,_tmpAmount,_tmpType,_tmpAccountId,_tmpCategoryId,_tmpNote,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg2);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
