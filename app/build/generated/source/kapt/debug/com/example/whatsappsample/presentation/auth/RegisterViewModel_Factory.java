package com.example.whatsappsample.presentation.auth;

import com.example.whatsappsample.domain.auth.usecase.SignUpUseCase;
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
public final class RegisterViewModel_Factory implements Factory<RegisterViewModel> {
  private final Provider<SignUpUseCase> signUpUseCaseProvider;

  private RegisterViewModel_Factory(Provider<SignUpUseCase> signUpUseCaseProvider) {
    this.signUpUseCaseProvider = signUpUseCaseProvider;
  }

  @Override
  public RegisterViewModel get() {
    return newInstance(signUpUseCaseProvider.get());
  }

  public static RegisterViewModel_Factory create(Provider<SignUpUseCase> signUpUseCaseProvider) {
    return new RegisterViewModel_Factory(signUpUseCaseProvider);
  }

  public static RegisterViewModel newInstance(SignUpUseCase signUpUseCase) {
    return new RegisterViewModel(signUpUseCase);
  }
}
