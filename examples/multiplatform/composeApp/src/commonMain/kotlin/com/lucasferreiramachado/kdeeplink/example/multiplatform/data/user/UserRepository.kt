package com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.model.AuthenticatedUser

interface UserRepository {

    fun authenticate(username: String, password: String): AuthenticatedUser?

    fun loggedUser(): AuthenticatedUser?
}