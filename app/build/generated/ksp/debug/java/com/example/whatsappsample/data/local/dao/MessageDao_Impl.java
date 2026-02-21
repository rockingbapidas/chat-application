package com.example.whatsappsample.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagingSource;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetPagingSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.whatsappsample.data.local.entity.MessageEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
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
public final class MessageDao_Impl implements MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MessageEntity> __insertionAdapterOfMessageEntity;

  private final EntityDeletionOrUpdateAdapter<MessageEntity> __deletionAdapterOfMessageEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMessagesForChat;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public MessageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessageEntity = new EntityInsertionAdapter<MessageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `messages` (`id`,`chatId`,`senderId`,`receiverId`,`content`,`type`,`timestamp`,`status`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MessageEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getChatId());
        statement.bindString(3, entity.getSenderId());
        statement.bindString(4, entity.getReceiverId());
        statement.bindString(5, entity.getContent());
        statement.bindString(6, entity.getType());
        statement.bindLong(7, entity.getTimestamp());
        statement.bindString(8, entity.getStatus());
      }
    };
    this.__deletionAdapterOfMessageEntity = new EntityDeletionOrUpdateAdapter<MessageEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `messages` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MessageEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteMessagesForChat = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM messages WHERE chatId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM messages";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final MessageEntity message, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMessageEntity.insert(message);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<MessageEntity> messages,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMessageEntity.insert(messages);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final MessageEntity message, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMessageEntity.handle(message);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteMessagesForChat(final String chatId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMessagesForChat.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, chatId);
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
          __preparedStmtOfDeleteMessagesForChat.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
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
    }, $completion);
  }

  @Override
  public PagingSource<Integer, MessageEntity> getMessagesPaging(final String chatId) {
    final String _sql = "SELECT * FROM messages WHERE chatId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, chatId);
    return new LimitOffsetPagingSource<MessageEntity>(_statement, __db, "messages") {
      @Override
      @NonNull
      protected List<MessageEntity> convertRows(@NonNull final Cursor cursor) {
        final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(cursor, "id");
        final int _cursorIndexOfChatId = CursorUtil.getColumnIndexOrThrow(cursor, "chatId");
        final int _cursorIndexOfSenderId = CursorUtil.getColumnIndexOrThrow(cursor, "senderId");
        final int _cursorIndexOfReceiverId = CursorUtil.getColumnIndexOrThrow(cursor, "receiverId");
        final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(cursor, "content");
        final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(cursor, "type");
        final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(cursor, "timestamp");
        final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(cursor, "status");
        final List<MessageEntity> _result = new ArrayList<MessageEntity>(cursor.getCount());
        while (cursor.moveToNext()) {
          final MessageEntity _item;
          final String _tmpId;
          _tmpId = cursor.getString(_cursorIndexOfId);
          final String _tmpChatId;
          _tmpChatId = cursor.getString(_cursorIndexOfChatId);
          final String _tmpSenderId;
          _tmpSenderId = cursor.getString(_cursorIndexOfSenderId);
          final String _tmpReceiverId;
          _tmpReceiverId = cursor.getString(_cursorIndexOfReceiverId);
          final String _tmpContent;
          _tmpContent = cursor.getString(_cursorIndexOfContent);
          final String _tmpType;
          _tmpType = cursor.getString(_cursorIndexOfType);
          final long _tmpTimestamp;
          _tmpTimestamp = cursor.getLong(_cursorIndexOfTimestamp);
          final String _tmpStatus;
          _tmpStatus = cursor.getString(_cursorIndexOfStatus);
          _item = new MessageEntity(_tmpId,_tmpChatId,_tmpSenderId,_tmpReceiverId,_tmpContent,_tmpType,_tmpTimestamp,_tmpStatus);
          _result.add(_item);
        }
        return _result;
      }
    };
  }

  @Override
  public Flow<List<MessageEntity>> getMessagesForChat(final String chatId) {
    final String _sql = "SELECT * FROM messages WHERE chatId = ? ORDER BY timestamp ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, chatId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"messages"}, new Callable<List<MessageEntity>>() {
      @Override
      @NonNull
      public List<MessageEntity> call() throws Exception {
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
          final List<MessageEntity> _result = new ArrayList<MessageEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MessageEntity _item;
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
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            _item = new MessageEntity(_tmpId,_tmpChatId,_tmpSenderId,_tmpReceiverId,_tmpContent,_tmpType,_tmpTimestamp,_tmpStatus);
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
  public Flow<MessageEntity> getMessageById(final String messageId) {
    final String _sql = "SELECT * FROM messages WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, messageId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"messages"}, new Callable<MessageEntity>() {
      @Override
      @Nullable
      public MessageEntity call() throws Exception {
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
          final MessageEntity _result;
          if (_cursor.moveToFirst()) {
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
            final String _tmpStatus;
            _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            _result = new MessageEntity(_tmpId,_tmpChatId,_tmpSenderId,_tmpReceiverId,_tmpContent,_tmpType,_tmpTimestamp,_tmpStatus);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
