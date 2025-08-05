package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.builder.model.Product
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.ProductsCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.detail.DetailUiState
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.detail.DetailViewModel
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.detail.composables.DetailScreen
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.list.ListViewModel
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.list.composables.ListScreen
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.ShoppingCartProduct
import kotlinx.serialization.Serializable

sealed class ProductListNavigationRoute {
    @Serializable object List: ProductListNavigationRoute()
    @Serializable data class Detail(val id: Int, val name: String, val price: Double): ProductListNavigationRoute()
}

sealed class ProductListCoordinatorAction: KCoordinatorAction {
    data object ShowList: ProductListCoordinatorAction()
    data class ShowDetail(val product: ShoppingCartProduct) : ProductListCoordinatorAction()
    data object GoBack : ProductListCoordinatorAction()
    data object StarBuyProductFlow : ProductListCoordinatorAction()
}

class ProductListCoordinator(
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<ProductListCoordinatorAction> {

    private var selectedProduct: ShoppingCartProduct? = null
    private var navHostController: NavHostController? = null

    override fun handle(action: ProductListCoordinatorAction) {
        when (action) {
            is ProductListCoordinatorAction.ShowList -> {
                navHostController?.navigate(ProductListNavigationRoute.List)
            }
            is ProductListCoordinatorAction.ShowDetail -> {
                selectedProduct = action.product
                navHostController?.navigate(ProductListNavigationRoute.Detail(
                    id = action.product.id,
                    name = action.product.name,
                    price = action.product.price,
                ))
            }
            is ProductListCoordinatorAction.GoBack -> {
                navHostController?.popBackStack()
            }

            is ProductListCoordinatorAction.StarBuyProductFlow -> {
                navHostController?.popBackStack()
                selectedProduct?.let {
                    parent.trigger(ProductsCoordinatorAction.StartPurchaseProduct(it))
                }
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        navGraphBuilder.apply {

            composable<ProductListNavigationRoute.List> {
                val viewModel = ListViewModel(coordinator = this@ProductListCoordinator)
                ListScreen(viewModel)
            }

            composable<ProductListNavigationRoute.Detail> {

                val route = it.toRoute<ProductListNavigationRoute.Detail>()
                val viewModel = DetailViewModel(
                    initialState = DetailUiState(
                        name = route.name,
                        price = "R$ ${route.price}"
                    ),
                    coordinator = this@ProductListCoordinator
                )
                DetailScreen(viewModel)
            }
        }
    }
}