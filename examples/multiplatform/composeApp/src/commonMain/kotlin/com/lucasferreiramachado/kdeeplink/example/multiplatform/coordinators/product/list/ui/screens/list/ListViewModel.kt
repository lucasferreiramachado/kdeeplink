package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.list

import androidx.lifecycle.ViewModel
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ProductListCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.ShoppingCartProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ListViewModel(
    initialState: ListUiState = ListUiState(),
    var coordinator: KCoordinator<ProductListCoordinatorAction>? = null,
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<ListUiState> = _state.asStateFlow()
    
    fun onEvent(event: ListUiEvent) {
        when (event) {
            is ListUiEvent.BackButtonPressed -> {
                coordinator?.trigger(ProductListCoordinatorAction.GoBack)
            }
            is ListUiEvent.ProductItemSelectedAt -> {
                val index = event.index
                val selectedProduct = ShoppingCartProduct(
                    id = index,
                    name = "Product $index",
                    price = index * 1.99
                )
                coordinator?.trigger(ProductListCoordinatorAction.ShowDetail(selectedProduct))
            }
        }
    }
}