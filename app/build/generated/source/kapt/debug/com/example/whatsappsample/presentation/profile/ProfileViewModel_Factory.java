package com.example.whatsappsample.presentation.profile;

import com.example.whatsappsample.domain.auth.usecase.GetCurrentUserUseCase;
import com.example.whatsappsample.domain.auth.usecase.SignOutUseCase;
import com.example.whatsappsample.domain.auth.usecase.UpdateProfileUseCase;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider;

  private final Provider<UpdateProfileUseCase> updateProfileUseCaseProvider;

  private final Provider<SignOutUseCase> signOutUseCaseProvider;

  private ProfileViewModel_Factory(Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<UpdateProfileUseCase> updateProfileUseCaseProvider,
      Provider<SignOutUseCase> signOutUseCaseProvider) {
    this.getCurrentUserUseCaseProvider = getCurrentUserUseCaseProvider;
    this.updateProfileUseCaseProvider = updateProfileUseCaseProvider;
    this.signOutUseCaseProvider = signOutUseCaseProvider;
  }

  @Override
  public ProfileViewModel get() {
    return newInstance(getCurrentUserUseCaseProvider.get(), updateProfileUseCaseProvider.get(), signOutUseCaseProvider.get());
  }

  public static ProfileViewModel_Factory create(
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<UpdateProfileUseCase> updateProfileUseCaseProvider,
      Provider<SignOutUseCase> signOutUseCaseProvider) {
    return new ProfileViewModel_Factory(getCurrentUserUseCaseProvider, updateProfileUseCaseProvider, signOutUseCaseProvider);
  }

  public static ProfileViewModel newInstance(GetCurrentUserUseCase getCurrentUserUseCase,
      UpdateProfileUseCase updateProfileUseCase, SignOutUseCase signOutUseCase) {
    return new ProfileViewModel(getCurrentUserUseCase, updateProfileUseCase, signOutUseCase);
  }
}
