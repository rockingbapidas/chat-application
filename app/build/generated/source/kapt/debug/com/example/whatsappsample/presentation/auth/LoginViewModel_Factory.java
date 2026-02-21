package com.example.whatsappsample.presentation.auth;

import com.example.whatsappsample.domain.auth.usecase.IsUserAuthenticatedUseCase;
import com.example.whatsappsample.domain.auth.usecase.SignInUseCase;
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
public final class LoginViewModel_Factory implements Factory<LoginViewModel> {
  private final Provider<SignInUseCase> signInUseCaseProvider;

  private final Provider<IsUserAuthenticatedUseCase> isUserAuthenticatedUseCaseProvider;

  private LoginViewModel_Factory(Provider<SignInUseCase> signInUseCaseProvider,
      Provider<IsUserAuthenticatedUseCase> isUserAuthenticatedUseCaseProvider) {
    this.signInUseCaseProvider = signInUseCaseProvider;
    this.isUserAuthenticatedUseCaseProvider = isUserAuthenticatedUseCaseProvider;
  }

  @Override
  public LoginViewModel get() {
    return newInstance(signInUseCaseProvider.get(), isUserAuthenticatedUseCaseProvider.get());
  }

  public static LoginViewModel_Factory create(Provider<SignInUseCase> signInUseCaseProvider,
      Provider<IsUserAuthenticatedUseCase> isUserAuthenticatedUseCaseProvider) {
    return new LoginViewModel_Factory(signInUseCaseProvider, isUserAuthenticatedUseCaseProvider);
  }

  public static LoginViewModel newInstance(SignInUseCase signInUseCase,
      IsUserAuthenticatedUseCase isUserAuthenticatedUseCase) {
    return new LoginViewModel(signInUseCase, isUserAuthenticatedUseCase);
  }
}
