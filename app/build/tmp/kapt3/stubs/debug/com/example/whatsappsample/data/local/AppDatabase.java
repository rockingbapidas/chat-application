package com.example.whatsappsample.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&\u00a8\u0006\u000b"}, d2 = {"Lcom/example/whatsappsample/data/local/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "chatDao", "Lcom/example/whatsappsample/data/local/dao/ChatDao;", "messageDao", "Lcom/example/whatsappsample/data/local/dao/MessageDao;", "outboxMessageDao", "Lcom/example/whatsappsample/data/local/dao/OutboxMessageDao;", "userDao", "Lcom/example/whatsappsample/data/local/dao/UserDao;", "app_debug"})
@androidx.room.Database(entities = {com.example.whatsappsample.data.local.entity.UserEntity.class, com.example.whatsappsample.data.local.entity.ChatEntity.class, com.example.whatsappsample.data.local.entity.MessageEntity.class, com.example.whatsappsample.data.local.entity.OutboxMessageEntity.class}, version = 2, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.whatsappsample.data.local.converter.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.whatsappsample.data.local.dao.UserDao userDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.whatsappsample.data.local.dao.ChatDao chatDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.whatsappsample.data.local.dao.MessageDao messageDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.whatsappsample.data.local.dao.OutboxMessageDao outboxMessageDao();
}