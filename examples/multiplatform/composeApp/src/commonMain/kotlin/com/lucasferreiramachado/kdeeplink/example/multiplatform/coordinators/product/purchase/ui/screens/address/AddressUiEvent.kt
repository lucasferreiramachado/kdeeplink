package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.ui.screens.address

sealed class AddressUiEvent {
    object BackButtonPressed : AddressUiEvent()
    data class AddressSelected(val address: String) : AddressUiEvent()
}