package com.example.whatsappsample.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.whatsappsample.data.local.converter.Converters;
import com.example.whatsappsample.data.local.entity.OutboxMessageEntity;
import com.example.whatsappsample.data.local.entity.OutboxStatus;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class OutboxMessageDao_Impl implements OutboxMessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<OutboxMessageEntity> __insertionAdapterOfOutboxMessageEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<OutboxMessageEntity> __deletionAdapterOfOutboxMessageEntity;

  private final EntityDeletionOrUpdateAdapter<OutboxMessageEntity> __updateAdapterOfOutboxMessageEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  public OutboxMessageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfOutboxMessageEntity = new EntityInsertionAdapter<OutboxMessageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `outbox_messages` (`id`,`chatId`,`senderId`,`receiverId`,`content`,`type`,`timestamp`,`status`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OutboxMessageEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getChatId());
        statement.bindString(3, entity.getSenderId());
        statement.bindString(4, entity.getReceiverId());
        statement.bindString(5, entity.getContent());
        statement.bindString(6, entity.getType());
        statement.bindLong(7, entity.getTimestamp());
        final String _tmp = __converters.fromOutboxStatus(entity.getStatus());
        if (_tmp == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp);
        }
      }
    };
    this.__deletionAdapterOfOutboxMessageEntity = new EntityDeletionOrUpdateAdapter<OutboxMessageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `outbox_messages` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OutboxMessageEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__updateAdapterOfOutboxMessageEntity = new EntityDeletionOrUpdateAdapter<OutboxMessageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `outbox_messages` SET `id` = ?,`chatId` = ?,`senderId` = ?,`receiverId` = ?,`content` = ?,`type` = ?,`timestamp` = ?,`status` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final OutboxMessageEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getChatId());
        statement.bindString(3, entity.getSenderId());
        statement.bindString(4, entity.getReceiverId());
        statement.bindString(5, entity.getContent());
        statement.bindString(6, entity.getType());
        statement.bindLong(7, entity.getTimestamp());
        final String _tmp = __converters.fromOutboxStatus(entity.getStatus());
        if (_tmp == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp);
        }
        statement.bindString(9, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM outbox_messages WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final OutboxMessageEntity message,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfOutboxMessageEntity.insert(message);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final OutboxMessageEntity message,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfOutboxMessageEntity.handle(message);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final OutboxMessageEntity message,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfOutboxMessageEntity.handle(message);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final String id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, id);
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
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getMessagesByStatus(final List<? extends OutboxStatus> statuses,
      final Continuation<? super List<OutboxMessageEntity>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM outbox_messages WHERE status IN (");
    final int _inputSize = statuses.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (OutboxStatus _item : statuses) {
      final String _tmp = __converters.fromOutboxStatus(_item);
      if (_tmp == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindString(_argIndex, _tmp);
      }
      _argIndex++;
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<OutboxMessageEntity>>() {
      @Override
      @NonNull
      public List<OutboxMessageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfChatId = CursorUtil.getColumnIndexOrThrow(_cursor, "chatId");
          final int _cursorIndexOfSenderId = CursorUtil.getColumnIndexOrThrow(_cursor, "senderId");
          final int _cursorIndexOfReceiverId = CursorUtil.getColumnIndexOrThrow(_cursor, "receiverId");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<OutboxMessageEntity> _result = new ArrayList<OutboxMessageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final OutboxMessageEntity _item_1;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpChatId;
            _tmpChatId = _cursor.getString(_cursorIndexOfChatId);
            final String _tmpSenderId;
            _tmpSenderId = _cursor.getString(_cursorIndexOfSenderId);
            final String _tmpReceiverId;
            _tmpReceiverId = _cursor.getString(_cursorIndexOfReceiverId);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final OutboxStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            final OutboxStatus _tmp_2 = __converters.toOutboxStatus(_tmp_1);
            if (_tmp_2 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.example.whatsappsample.data.local.entity.OutboxStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_2;
            }
            _item_1 = new OutboxMessageEntity(_tmpId,_tmpChatId,_tmpSenderId,_tmpReceiverId,_tmpContent,_tmpType,_tmpTimestamp,_tmpStatus);
            _result.add(_item_1);
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
  public Flow<List<OutboxMessageEntity>> observeAll() {
    final String _sql = "SELECT * FROM outbox_messages ORDER BY timestamp ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"outbox_messages"}, new Callable<List<OutboxMessageEntity>>() {
      @Override
      @NonNull
      public List<OutboxMessageEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfChatId = CursorUtil.getColumnIndexOrThrow(_cursor, "chatId");
          final int _cursorIndexOfSenderId = CursorUtil.getColumnIndexOrThrow(_cursor, "senderId");
          final int _cursorIndexOfReceiverId = CursorUtil.getColumnIndexOrThrow(_cursor, "receiverId");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<OutboxMessageEntity> _result = new ArrayList<OutboxMessageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final OutboxMessageEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpChatId;
            _tmpChatId = _cursor.getString(_cursorIndexOfChatId);
            final String _tmpSenderId;
            _tmpSenderId = _cursor.getString(_cursorIndexOfSenderId);
            final String _tmpReceiverId;
            _tmpReceiverId = _cursor.getString(_cursorIndexOfReceiverId);
            final String _tmpContent;
            _tmpContent = _cursor.getString(_cursorIndexOfContent);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final OutboxStatus _tmpStatus;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfStatus);
            }
            final OutboxStatus _tmp_1 = __converters.toOutboxStatus(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'com.example.whatsappsample.data.local.entity.OutboxStatus', but it was NULL.");
            } else {
              _tmpStatus = _tmp_1;
            }
            _item = new OutboxMessageEntity(_tmpId,_tmpChatId,_tmpSenderId,_tmpReceiverId,_tmpContent,_tmpType,_tmpTimestamp,_tmpStatus);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
