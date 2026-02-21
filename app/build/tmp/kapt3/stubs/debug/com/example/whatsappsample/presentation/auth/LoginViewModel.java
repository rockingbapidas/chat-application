package com.example.whatsappsample.presentation.auth;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0013"}, d2 = {"Lcom/example/whatsappsample/presentation/auth/LoginViewModel;", "Landroidx/lifecycle/ViewModel;", "signInUseCase", "Lcom/example/whatsappsample/domain/auth/usecase/SignInUseCase;", "isUserAuthenticatedUseCase", "Lcom/example/whatsappsample/domain/auth/usecase/IsUserAuthenticatedUseCase;", "(Lcom/example/whatsappsample/domain/auth/usecase/SignInUseCase;Lcom/example/whatsappsample/domain/auth/usecase/IsUserAuthenticatedUseCase;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/whatsappsample/presentation/auth/LoginState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "login", "", "email", "", "password", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class LoginViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.whatsappsample.domain.auth.usecase.SignInUseCase signInUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.whatsappsample.domain.auth.usecase.IsUserAuthenticatedUseCase isUserAuthenticatedUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.whatsappsample.presentation.auth.LoginState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.whatsappsample.presentation.auth.LoginState> state = null;
    
    @javax.inject.Inject()
    public LoginViewModel(@org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.domain.auth.usecase.SignInUseCase signInUseCase, @org.jetbrains.annotations.NotNull()
    com.example.whatsappsample.domain.auth.usecase.IsUserAuthenticatedUseCase isUserAuthenticatedUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.whatsappsample.presentation.auth.LoginState> getState() {
        return null;
    }
    
    public final void login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String password) {
    }
}