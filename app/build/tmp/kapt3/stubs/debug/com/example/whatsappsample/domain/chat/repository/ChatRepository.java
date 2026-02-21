package com.example.whatsappsample.domain.chat.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\n\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u0014\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\r0\u0003H&J\u001c\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\r0\u00032\u0006\u0010\n\u001a\u00020\u0006H&J\u001c\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00110\u00032\u0006\u0010\n\u001a\u00020\u0006H&J\u0016\u0010\u0012\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0007J\u001e\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u00a6@\u00a2\u0006\u0002\u0010\u0015J\u001e\u0010\u0016\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u000fH\u00a6@\u00a2\u0006\u0002\u0010\u0018\u00a8\u0006\u0019"}, d2 = {"Lcom/example/whatsappsample/domain/chat/repository/ChatRepository;", "", "createChat", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/whatsappsample/domain/chat/model/Chat;", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteChat", "", "chatId", "getChat", "getChats", "", "getMessages", "Lcom/example/whatsappsample/domain/chat/model/Message;", "getMessagesPaging", "Landroidx/paging/PagingData;", "markChatAsRead", "sendMessage", "content", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateChatLastMessage", "message", "(Ljava/lang/String;Lcom/example/whatsappsample/domain/chat/model/Message;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ChatRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.whatsappsample.domain.chat.model.Chat>> getChats();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getChat(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.example.whatsappsample.domain.chat.model.Chat>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.whatsappsample.domain.chat.model.Message>> getMessages(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.example.whatsappsample.domain.chat.model.Message>> getMessagesPaging(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    java.lang.String content, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createChat(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlinx.coroutines.flow.Flow<com.example.whatsappsample.domain.chat.model.Chat>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteChat(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object markChatAsRead(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateChatLastMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String chatId, @org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.domain.chat.model.Message message, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}