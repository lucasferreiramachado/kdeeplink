package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.ui.screens.address

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.PurchaseProductCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.domain.usecases.SetPurchaseAddressUseCase
import com.lucasferreiramachado.kdeeplink.example.multiplatform.di.PurchaseRepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddressViewModel(
    initialState: AddressUiState = AddressUiState(),
    val coordinator: KCoordinator<PurchaseProductCoordinatorAction>? = null,
    val setPurchaseAddressUseCase: SetPurchaseAddressUseCase = SetPurchaseAddressUseCase(
        PurchaseRepositoryFactory.create()
    )
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<AddressUiState> = _state.asStateFlow()
    
    fun onEvent(event: AddressUiEvent) {
        when (event) {
            is AddressUiEvent.BackButtonPressed -> {
                coordinator?.trigger(PurchaseProductCoordinatorAction.GoBack)
            }
            is AddressUiEvent.AddressSelected -> {
                setPurchaseAddressUseCase.execute(event.address)
                coordinator?.trigger(PurchaseProductCoordinatorAction.ShowPayment)
            }
        }
    }
}

