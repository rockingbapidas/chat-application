package com.example.whatsappsample.presentation.chat;

import androidx.lifecycle.SavedStateHandle;
import com.example.whatsappsample.domain.auth.usecase.GetCurrentUserIdUseCase;
import com.example.whatsappsample.domain.chat.usecase.GetChatUseCase;
import com.example.whatsappsample.domain.chat.usecase.GetMessagesUseCase;
import com.example.whatsappsample.domain.chat.usecase.SendMessageUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class ChatViewModel_Factory implements Factory<ChatViewModel> {
  private final Provider<GetChatUseCase> getChatUseCaseProvider;

  private final Provider<GetMessagesUseCase> getMessagesPagingUseCaseProvider;

  private final Provider<SendMessageUseCase> sendMessageUseCaseProvider;

  private final Provider<GetCurrentUserIdUseCase> getCurrentUserIdUseCaseProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private ChatViewModel_Factory(Provider<GetChatUseCase> getChatUseCaseProvider,
      Provider<GetMessagesUseCase> getMessagesPagingUseCaseProvider,
      Provider<SendMessageUseCase> sendMessageUseCaseProvider,
      Provider<GetCurrentUserIdUseCase> getCurrentUserIdUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.getChatUseCaseProvider = getChatUseCaseProvider;
    this.getMessagesPagingUseCaseProvider = getMessagesPagingUseCaseProvider;
    this.sendMessageUseCaseProvider = sendMessageUseCaseProvider;
    this.getCurrentUserIdUseCaseProvider = getCurrentUserIdUseCaseProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ChatViewModel get() {
    return newInstance(getChatUseCaseProvider.get(), getMessagesPagingUseCaseProvider.get(), sendMessageUseCaseProvider.get(), getCurrentUserIdUseCaseProvider.get(), savedStateHandleProvider.get());
  }

  public static ChatViewModel_Factory create(Provider<GetChatUseCase> getChatUseCaseProvider,
      Provider<GetMessagesUseCase> getMessagesPagingUseCaseProvider,
      Provider<SendMessageUseCase> sendMessageUseCaseProvider,
      Provider<GetCurrentUserIdUseCase> getCurrentUserIdUseCaseProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ChatViewModel_Factory(getChatUseCaseProvider, getMessagesPagingUseCaseProvider, sendMessageUseCaseProvider, getCurrentUserIdUseCaseProvider, savedStateHandleProvider);
  }

  public static ChatViewModel newInstance(GetChatUseCase getChatUseCase,
                                          GetMessagesUseCase getMessagesUseCase, SendMessageUseCase sendMessageUseCase,
                                          GetCurrentUserIdUseCase getCurrentUserIdUseCase, SavedStateHandle savedStateHandle) {
    return new ChatViewModel(getChatUseCase, getMessagesUseCase, sendMessageUseCase, getCurrentUserIdUseCase, savedStateHandle);
  }
}
