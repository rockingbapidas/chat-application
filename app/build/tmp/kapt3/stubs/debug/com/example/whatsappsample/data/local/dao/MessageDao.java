package com.example.whatsappsample.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\'J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00110\u000e2\u0006\u0010\n\u001a\u00020\u000bH\'J\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\n\u001a\u00020\u000bH\'J\u0016\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/example/whatsappsample/data/local/dao/MessageDao;", "", "delete", "", "message", "Lcom/example/whatsappsample/data/local/entity/MessageEntity;", "(Lcom/example/whatsappsample/data/local/entity/MessageEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMessagesForChat", "chatId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMessageById", "Lkotlinx/coroutines/flow/Flow;", "messageId", "getMessagesForChat", "", "getMessagesPaging", "Landroidx/paging/PagingSource;", "", "insert", "insertAll", "messages", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface MessageDao {
    
    @androidx.room.Query(value = "SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp DESC")
    @org.jetbrains.annotations.NotNull()
    public abstract androidx.paging.PagingSource<java.lang.Integer, com.example.whatsappsample.data.local.entity.MessageEntity> getMessagesPaging(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId);
    
    @androidx.room.Query(value = "SELECT * FROM messages WHERE chatId = :chatId ORDER BY timestamp ASC")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.whatsappsample.data.local.entity.MessageEntity>> getMessagesForChat(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId);
    
    @androidx.room.Query(value = "SELECT * FROM messages WHERE id = :messageId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.example.whatsappsample.data.local.entity.MessageEntity> getMessageById(@org.jetbrains.annotations.NotNull()
    java.lang.String messageId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.data.local.entity.MessageEntity message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.whatsappsample.data.local.entity.MessageEntity> messages, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.data.local.entity.MessageEntity message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM messages WHERE chatId = :chatId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteMessagesForChat(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM messages")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}