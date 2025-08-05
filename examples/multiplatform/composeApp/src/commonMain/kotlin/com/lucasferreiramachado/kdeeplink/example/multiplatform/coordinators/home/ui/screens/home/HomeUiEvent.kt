package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.home.ui.screens.home

sealed class HomeUiEvent {
    object SignOutButtonPressed : HomeUiEvent()
    object ProductsButtonPressed : HomeUiEvent()
}