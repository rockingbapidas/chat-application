package com.example.whatsappsample.presentation.chat;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B/\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\fJ\b\u0010\u001c\u001a\u00020\u001dH\u0002J\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u0011R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u00a8\u0006 "}, d2 = {"Lcom/example/whatsappsample/presentation/chat/ChatViewModel;", "Landroidx/lifecycle/ViewModel;", "getChatUseCase", "Lcom/example/whatsappsample/domain/chat/usecase/GetChatUseCase;", "getMessagesPagingUseCase", "Lcom/example/whatsappsample/domain/chat/usecase/GetMessagesPagingUseCase;", "sendMessageUseCase", "Lcom/example/whatsappsample/domain/chat/usecase/SendMessageUseCase;", "getCurrentUserIdUseCase", "Lcom/example/whatsappsample/domain/auth/usecase/GetCurrentUserIdUseCase;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "(Lcom/example/whatsappsample/domain/chat/usecase/GetChatUseCase;Lcom/example/whatsappsample/domain/chat/usecase/GetMessagesPagingUseCase;Lcom/example/whatsappsample/domain/chat/usecase/SendMessageUseCase;Lcom/example/whatsappsample/domain/auth/usecase/GetCurrentUserIdUseCase;Landroidx/lifecycle/SavedStateHandle;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/whatsappsample/presentation/chat/ChatState;", "chatId", "", "messages", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "Lcom/example/whatsappsample/domain/chat/model/Message;", "getMessages", "()Lkotlinx/coroutines/flow/Flow;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "loadChat", "", "sendMessage", "content", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ChatViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.whatsappsample.domain.chat.usecase.GetChatUseCase getChatUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.whatsappsample.domain.chat.usecase.GetMessagesPagingUseCase getMessagesPagingUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.whatsappsample.domain.chat.usecase.SendMessageUseCase sendMessageUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.whatsappsample.domain.auth.usecase.GetCurrentUserIdUseCase getCurrentUserIdUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String chatId = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.whatsappsample.presentation.chat.ChatState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.whatsappsample.presentation.chat.ChatState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.example.whatsappsample.domain.chat.model.Message>> messages = null;
    
    @javax.inject.Inject()
    public ChatViewModel(@org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.domain.chat.usecase.GetChatUseCase getChatUseCase, @org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.domain.chat.usecase.GetMessagesPagingUseCase getMessagesPagingUseCase, @org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.domain.chat.usecase.SendMessageUseCase sendMessageUseCase, @org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.domain.auth.usecase.GetCurrentUserIdUseCase getCurrentUserIdUseCase, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.whatsappsample.presentation.chat.ChatState> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<androidx.paging.PagingData<com.example.whatsappsample.domain.chat.model.Message>> getMessages() {
        return null;
    }
    
    private final void loadChat() {
    }
    
    public final void sendMessage(@org.jetbrains.annotations.NotNull()
    java.lang.String content) {
    }
}