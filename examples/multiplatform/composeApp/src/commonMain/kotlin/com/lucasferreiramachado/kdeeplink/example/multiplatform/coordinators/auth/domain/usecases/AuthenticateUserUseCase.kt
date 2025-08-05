package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.domain.usecases

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.UserRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.model.AuthenticatedUser

class AuthenticateUserUseCase(
    val repository: UserRepository
) {
    fun execute(username: String, password: String): AuthenticatedUser? = repository.authenticate(username, password)
}