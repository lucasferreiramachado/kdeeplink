package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.ui.screens.checkout

sealed class CheckoutUiEvent {
    object BackButtonPressed : CheckoutUiEvent()
    object ConfirmButtonPressed : CheckoutUiEvent()
}