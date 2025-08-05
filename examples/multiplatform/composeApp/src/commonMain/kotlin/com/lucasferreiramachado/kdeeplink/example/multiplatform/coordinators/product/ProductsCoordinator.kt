package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.di.PurchaseProductCoordinatorFactory
import com.lucasferreiramachado.kdeeplink.example.multiplatform.di.ProductListCoordinatorFactory
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.PurchaseProductCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ProductListCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.ShoppingCartProduct

sealed class ProductsCoordinatorAction: KCoordinatorAction {
    data object StartProductList : ProductsCoordinatorAction()
    data class StartProductDetail(val product: ShoppingCartProduct) : ProductsCoordinatorAction()
    data class StartPurchaseProduct(val product: ShoppingCartProduct) : ProductsCoordinatorAction()
}

class ProductsCoordinator(
    purchaseProductCoordinatorFactory: PurchaseProductCoordinatorFactory = PurchaseProductCoordinatorFactory(),
    productListCoordinatorFactory: ProductListCoordinatorFactory = ProductListCoordinatorFactory(),
    override val parent: KCoordinator<*>
) : ComposeKCoordinator<ProductsCoordinatorAction> {
    private val purchaseProductCoordinator = purchaseProductCoordinatorFactory.create(parent = this)
    private val productListCoordinator = productListCoordinatorFactory.create(parent = this)

    private var navHostController: NavHostController? = null

    override fun handle(action: ProductsCoordinatorAction) {
        when (action) {
            is ProductsCoordinatorAction.StartPurchaseProduct -> {
                purchaseProductCoordinator.trigger(
                    PurchaseProductCoordinatorAction.StartPurchase(action.product)
                )
            }
            is ProductsCoordinatorAction.StartProductList -> {
                productListCoordinator.trigger(ProductListCoordinatorAction.ShowList)
            }
            is ProductsCoordinatorAction.StartProductDetail ->  {
                productListCoordinator.trigger(ProductListCoordinatorAction.ShowDetail(action.product))
            }
        }
    }
    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {
        this.navHostController = navHostController

        purchaseProductCoordinator.setupNavigation(navGraphBuilder, navHostController)
        productListCoordinator.setupNavigation(navGraphBuilder, navHostController)
    }
}