package kz.diploma.securefinance.data.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
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
import kz.diploma.securefinance.data.entity.ExchangeRate;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ExchangeRateDao_Impl implements ExchangeRateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExchangeRate> __insertionAdapterOfExchangeRate;

  public ExchangeRateDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExchangeRate = new EntityInsertionAdapter<ExchangeRate>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `exchange_rates` (`currency`,`rateToKzt`,`updatedAt`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExchangeRate entity) {
        if (entity.getCurrency() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getCurrency());
        }
        statement.bindDouble(2, entity.getRateToKzt());
        statement.bindLong(3, entity.getUpdatedAt());
      }
    };
  }

  @Override
  public Object upsert(final ExchangeRate rate, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfExchangeRate.insert(rate);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertAll(final List<ExchangeRate> rates,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfExchangeRate.insert(rates);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<ExchangeRate>> getAllLive() {
    final String _sql = "SELECT * FROM exchange_rates";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"exchange_rates"}, false, new Callable<List<ExchangeRate>>() {
      @Override
      @Nullable
      public List<ExchangeRate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfRateToKzt = CursorUtil.getColumnIndexOrThrow(_cursor, "rateToKzt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ExchangeRate> _result = new ArrayList<ExchangeRate>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExchangeRate _item;
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final double _tmpRateToKzt;
            _tmpRateToKzt = _cursor.getDouble(_cursorIndexOfRateToKzt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ExchangeRate(_tmpCurrency,_tmpRateToKzt,_tmpUpdatedAt);
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
  public Object getAll(final Continuation<? super List<ExchangeRate>> $completion) {
    final String _sql = "SELECT * FROM exchange_rates";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ExchangeRate>>() {
      @Override
      @NonNull
      public List<ExchangeRate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfRateToKzt = CursorUtil.getColumnIndexOrThrow(_cursor, "rateToKzt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ExchangeRate> _result = new ArrayList<ExchangeRate>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExchangeRate _item;
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final double _tmpRateToKzt;
            _tmpRateToKzt = _cursor.getDouble(_cursorIndexOfRateToKzt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ExchangeRate(_tmpCurrency,_tmpRateToKzt,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getByCurrency(final String currency,
      final Continuation<? super ExchangeRate> $completion) {
    final String _sql = "SELECT * FROM exchange_rates WHERE currency = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (currency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, currency);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExchangeRate>() {
      @Override
      @Nullable
      public ExchangeRate call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfRateToKzt = CursorUtil.getColumnIndexOrThrow(_cursor, "rateToKzt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ExchangeRate _result;
          if (_cursor.moveToFirst()) {
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final double _tmpRateToKzt;
            _tmpRateToKzt = _cursor.getDouble(_cursorIndexOfRateToKzt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ExchangeRate(_tmpCurrency,_tmpRateToKzt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
