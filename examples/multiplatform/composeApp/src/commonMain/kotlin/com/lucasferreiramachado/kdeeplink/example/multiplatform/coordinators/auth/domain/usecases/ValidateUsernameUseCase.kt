package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.domain.usecases

class ValidateUsernameUseCase {

    fun execute(input: String): String? {
        if (input.length < 4 || input.isBlank()) {
            return "Insert 4 characters username at least"
        }
        return null
    }
}