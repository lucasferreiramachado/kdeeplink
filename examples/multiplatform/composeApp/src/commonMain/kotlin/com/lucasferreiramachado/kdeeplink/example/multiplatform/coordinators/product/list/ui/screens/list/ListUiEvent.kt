package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.list

sealed class ListUiEvent {
    object BackButtonPressed : ListUiEvent()
    data class ProductItemSelectedAt(val index: Int) : ListUiEvent()
}