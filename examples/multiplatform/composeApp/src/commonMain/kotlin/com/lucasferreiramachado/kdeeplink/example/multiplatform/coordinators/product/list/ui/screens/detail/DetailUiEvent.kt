package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.detail

sealed class DetailUiEvent {
    object BackButtonPressed : DetailUiEvent()
    object BuyButtonPressed : DetailUiEvent()
}