package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.login.ui.screens.login

data class LoginUiState(
    var username: String = "",
    var usernameError: String? = null,
    var password: String = "",
    var passwordError: String? = null,
    var isVisiblePassword: Boolean = false,
    var signInButtonEnabled: Boolean = true
)