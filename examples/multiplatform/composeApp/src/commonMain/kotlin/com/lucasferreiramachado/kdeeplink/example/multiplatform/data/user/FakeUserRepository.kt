package com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.user.model.AuthenticatedUser

class FakeUserRepository(
    private var authenticatedUser: AuthenticatedUser? = null
): UserRepository {

    override fun authenticate(
        username: String,
        password: String,
    ): AuthenticatedUser? {
        authenticatedUser = AuthenticatedUser(
            id = "123",
            username = username,
            name = "John"
        )
        return authenticatedUser
    }

    override fun loggedUser(): AuthenticatedUser? = authenticatedUser
}