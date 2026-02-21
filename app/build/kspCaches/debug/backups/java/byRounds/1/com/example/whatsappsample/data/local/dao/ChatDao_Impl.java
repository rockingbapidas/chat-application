package com.example.whatsappsample.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.whatsappsample.data.local.converter.Converters;
import com.example.whatsappsample.data.local.entity.ChatEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalStateException;
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
public final class ChatDao_Impl implements ChatDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ChatEntity> __insertionAdapterOfChatEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<ChatEntity> __deletionAdapterOfChatEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteChat;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public ChatDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChatEntity = new EntityInsertionAdapter<ChatEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `chats` (`id`,`participants`,`lastMessageId`,`unreadCount`,`isGroup`,`groupName`,`groupPictureUrl`,`createdAt`,`updatedAt`) VALUES (?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ChatEntity entity) {
        statement.bindString(1, entity.getId());
        final String _tmp = __converters.fromStringList(entity.getParticipants());
        statement.bindString(2, _tmp);
        if (entity.getLastMessageId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getLastMessageId());
        }
        statement.bindLong(4, entity.getUnreadCount());
        final int _tmp_1 = entity.isGroup() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        if (entity.getGroupName() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getGroupName());
        }
        if (entity.getGroupPictureUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getGroupPictureUrl());
        }
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfChatEntity = new EntityDeletionOrUpdateAdapter<ChatEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `chats` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ChatEntity entity) {
        statement.bindString(1, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteChat = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM chats WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM chats";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ChatEntity chat, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfChatEntity.insert(chat);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertAll(final List<ChatEntity> chats,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfChatEntity.insert(chats);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final ChatEntity chat, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfChatEntity.handle(chat);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteChat(final String chatId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteChat.acquire();
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
          __preparedStmtOfDeleteChat.release(_stmt);
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
  public Flow<List<ChatEntity>> getAllChats() {
    final String _sql = "SELECT * FROM chats ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"chats"}, new Callable<List<ChatEntity>>() {
      @Override
      @NonNull
      public List<ChatEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
          final int _cursorIndexOfLastMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessageId");
          final int _cursorIndexOfUnreadCount = CursorUtil.getColumnIndexOrThrow(_cursor, "unreadCount");
          final int _cursorIndexOfIsGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "isGroup");
          final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "groupName");
          final int _cursorIndexOfGroupPictureUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "groupPictureUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ChatEntity> _result = new ArrayList<ChatEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ChatEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final List<String> _tmpParticipants;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfParticipants);
            final List<String> _tmp_1 = __converters.toStringList(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.List<java.lang.String>', but it was NULL.");
            } else {
              _tmpParticipants = _tmp_1;
            }
            final String _tmpLastMessageId;
            if (_cursor.isNull(_cursorIndexOfLastMessageId)) {
              _tmpLastMessageId = null;
            } else {
              _tmpLastMessageId = _cursor.getString(_cursorIndexOfLastMessageId);
            }
            final int _tmpUnreadCount;
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount);
            final boolean _tmpIsGroup;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsGroup);
            _tmpIsGroup = _tmp_2 != 0;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final String _tmpGroupPictureUrl;
            if (_cursor.isNull(_cursorIndexOfGroupPictureUrl)) {
              _tmpGroupPictureUrl = null;
            } else {
              _tmpGroupPictureUrl = _cursor.getString(_cursorIndexOfGroupPictureUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ChatEntity(_tmpId,_tmpParticipants,_tmpLastMessageId,_tmpUnreadCount,_tmpIsGroup,_tmpGroupName,_tmpGroupPictureUrl,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<ChatEntity> getChatById(final String chatId) {
    final String _sql = "SELECT * FROM chats WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, chatId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"chats"}, new Callable<ChatEntity>() {
      @Override
      @Nullable
      public ChatEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfParticipants = CursorUtil.getColumnIndexOrThrow(_cursor, "participants");
          final int _cursorIndexOfLastMessageId = CursorUtil.getColumnIndexOrThrow(_cursor, "lastMessageId");
          final int _cursorIndexOfUnreadCount = CursorUtil.getColumnIndexOrThrow(_cursor, "unreadCount");
          final int _cursorIndexOfIsGroup = CursorUtil.getColumnIndexOrThrow(_cursor, "isGroup");
          final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "groupName");
          final int _cursorIndexOfGroupPictureUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "groupPictureUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ChatEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final List<String> _tmpParticipants;
            final String _tmp;
            _tmp = _cursor.getString(_cursorIndexOfParticipants);
            final List<String> _tmp_1 = __converters.toStringList(_tmp);
            if (_tmp_1 == null) {
              throw new IllegalStateException("Expected NON-NULL 'java.util.List<java.lang.String>', but it was NULL.");
            } else {
              _tmpParticipants = _tmp_1;
            }
            final String _tmpLastMessageId;
            if (_cursor.isNull(_cursorIndexOfLastMessageId)) {
              _tmpLastMessageId = null;
            } else {
              _tmpLastMessageId = _cursor.getString(_cursorIndexOfLastMessageId);
            }
            final int _tmpUnreadCount;
            _tmpUnreadCount = _cursor.getInt(_cursorIndexOfUnreadCount);
            final boolean _tmpIsGroup;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsGroup);
            _tmpIsGroup = _tmp_2 != 0;
            final String _tmpGroupName;
            if (_cursor.isNull(_cursorIndexOfGroupName)) {
              _tmpGroupName = null;
            } else {
              _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
            }
            final String _tmpGroupPictureUrl;
            if (_cursor.isNull(_cursorIndexOfGroupPictureUrl)) {
              _tmpGroupPictureUrl = null;
            } else {
              _tmpGroupPictureUrl = _cursor.getString(_cursorIndexOfGroupPictureUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ChatEntity(_tmpId,_tmpParticipants,_tmpLastMessageId,_tmpUnreadCount,_tmpIsGroup,_tmpGroupName,_tmpGroupPictureUrl,_tmpCreatedAt,_tmpUpdatedAt);
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
