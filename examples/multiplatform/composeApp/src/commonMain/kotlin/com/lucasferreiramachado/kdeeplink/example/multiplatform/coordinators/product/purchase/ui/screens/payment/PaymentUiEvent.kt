package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.ui.screens.payment

sealed class PaymentUiEvent {
    object BackButtonPressed : PaymentUiEvent()
    object CreditCardSelected : PaymentUiEvent()
    object PixSelected : PaymentUiEvent()
}