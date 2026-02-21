package com.example.whatsappsample.presentation.chat;

import com.example.whatsappsample.domain.chat.usecase.GetChatsUseCase;
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
public final class ChatListViewModel_Factory implements Factory<ChatListViewModel> {
  private final Provider<GetChatsUseCase> getChatsUseCaseProvider;

  private ChatListViewModel_Factory(Provider<GetChatsUseCase> getChatsUseCaseProvider) {
    this.getChatsUseCaseProvider = getChatsUseCaseProvider;
  }

  @Override
  public ChatListViewModel get() {
    return newInstance(getChatsUseCaseProvider.get());
  }

  public static ChatListViewModel_Factory create(
      Provider<GetChatsUseCase> getChatsUseCaseProvider) {
    return new ChatListViewModel_Factory(getChatsUseCaseProvider);
  }

  public static ChatListViewModel newInstance(GetChatsUseCase getChatsUseCase) {
    return new ChatListViewModel(getChatsUseCase);
  }
}
