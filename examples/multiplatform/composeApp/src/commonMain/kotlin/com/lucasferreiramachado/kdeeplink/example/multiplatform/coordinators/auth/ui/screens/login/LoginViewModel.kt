package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.ui.screens.login

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.AuthCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.domain.usecases.AuthenticateUserUseCase
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.domain.usecases.ValidateLoginInputUseCase
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.domain.usecases.ValidatePasswordUseCase
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.domain.usecases.ValidateUsernameUseCase
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.login.ui.screens.login.LoginUiState
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.UserRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.model.AuthenticatedUser
import com.lucasferreiramachado.kdeeplink.example.multiplatform.di.UserRepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    initialState: LoginUiState = LoginUiState(),
    val coordinator: KCoordinator<AuthCoordinatorAction>? = null,
    val authenticateUserUseCase: AuthenticateUserUseCase = AuthenticateUserUseCase(
        UserRepositoryFactory.create()
    )
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)

    val state: StateFlow<LoginUiState> = _state.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.UsernameChanged -> {
                val username = event.username
                val usernameError = ValidateUsernameUseCase().execute(username)
                _state.update { state ->
                    state.copy(
                        username = username,
                        usernameError = usernameError
                    )
                }
            }
            is LoginUiEvent.PasswordChanged -> {
                val password = event.password
                val passwordError = ValidatePasswordUseCase().execute(password)
                _state.update { state ->
                    state.copy(
                        password = password,
                        passwordError = passwordError
                    )
                }
            }
            is LoginUiEvent.PasswordVisibilityChanged -> {
                _state.update { state ->
                    state.copy(
                        isVisiblePassword = !state.isVisiblePassword,
                    )
                }
            }
            is LoginUiEvent.SignInButtonPressed -> {
                val result = ValidateLoginInputUseCase().execute(
                    state.value.username,
                    state.value.password
                )
                _state.update { state ->
                    state.copy(
                        passwordError = result.passwordError,
                        usernameError = result.usernameError,
                    )
                }
                if (result.isValid) {
                    val authenticateUser = authenticateUserUseCase.execute(
                        username = state.value.username,
                        password = state.value.password,
                    )
                    when (authenticateUser) {
                        null -> {
                            _state.update { state ->
                                state.copy(
                                    password = "",
                                    passwordError = "Invalid credentials",
                                )
                            }
                        }
                        else -> {
                            coordinator?.trigger(AuthCoordinatorAction.Authenticated(authenticateUser.name))
                        }
                    }
                }
            }
        }
    }
}

