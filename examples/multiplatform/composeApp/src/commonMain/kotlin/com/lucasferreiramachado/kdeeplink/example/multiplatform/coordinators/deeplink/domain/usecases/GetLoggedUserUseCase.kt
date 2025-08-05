package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.domain.usecases

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.UserRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.model.AuthenticatedUser

class GetLoggedUserUseCase(
    val repository: UserRepository
) {
    fun execute(): AuthenticatedUser? = repository.loggedUser()
}