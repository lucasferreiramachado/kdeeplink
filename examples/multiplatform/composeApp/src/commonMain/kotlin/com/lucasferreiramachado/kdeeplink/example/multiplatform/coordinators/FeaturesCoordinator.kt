package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.lucasferreiramachado.kcoordinator.KCoordinator
import com.lucasferreiramachado.kcoordinator.KCoordinatorAction
import com.lucasferreiramachado.kcoordinator.compose.ComposeKCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.builder.model.Product
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.auth.AuthCoordinatorAction.*
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.home.HomeCoordinatorAction.ShowHomeScreen
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.ProductsCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.ShoppingCartProduct
import com.lucasferreiramachado.kdeeplink.example.multiplatform.di.AuthCoordinatorFactory
import com.lucasferreiramachado.kdeeplink.example.multiplatform.di.HomeCoordinatorFactory
import com.lucasferreiramachado.kdeeplink.example.multiplatform.di.ProductsCoordinatorFactory

sealed class FeaturesNavigationRoute(val route: String) {
    object Auth : FeaturesNavigationRoute("auth")
    object Home : FeaturesNavigationRoute("main")
    object Feature : FeaturesNavigationRoute("feature")
}

sealed class FeaturesCoordinatorAction: KCoordinatorAction {
    data object StartLoginFlow : FeaturesCoordinatorAction()
    data class StartHomeFlow(val username: String) : FeaturesCoordinatorAction()
    data object StartProductListFlow : FeaturesCoordinatorAction()
    data class AuthenticateUserAndTriggerAction(val action: FeaturesCoordinatorAction) : FeaturesCoordinatorAction()
    data class ProductDetailFlow(val product: Product) : FeaturesCoordinatorAction()
    data class BuyProductFlow(val product: Product) : FeaturesCoordinatorAction()
}

class FeaturesCoordinator(
    authCoordinatorFactory: AuthCoordinatorFactory = AuthCoordinatorFactory(),
    homeCoordinatorFactory: HomeCoordinatorFactory = HomeCoordinatorFactory(),
    productsCoordinatorFactory: ProductsCoordinatorFactory = ProductsCoordinatorFactory(),
    override val parent: KCoordinator<*>
) : ComposeKCoordinator <FeaturesCoordinatorAction> {

    private val authCoordinator = authCoordinatorFactory.create(parent = this)
    private val homeCoordinator = homeCoordinatorFactory.create(parent = this)
    private val productsCoordinator = productsCoordinatorFactory.create(parent = this)

    override fun handle(action: FeaturesCoordinatorAction) {
        when (action) {
            is FeaturesCoordinatorAction.StartLoginFlow -> {
                authCoordinator.trigger(
                    StartLogin { username ->
                        trigger(FeaturesCoordinatorAction.StartHomeFlow(username = username))
                    }
                )
            }
            is FeaturesCoordinatorAction.StartHomeFlow -> {
                val username = action.username
                homeCoordinator.trigger(ShowHomeScreen(username = username))
            }
            is FeaturesCoordinatorAction.StartProductListFlow -> {
                productsCoordinator.trigger(ProductsCoordinatorAction.StartProductList)
            }
            is FeaturesCoordinatorAction.ProductDetailFlow -> {
                productsCoordinator.trigger(ProductsCoordinatorAction.StartProductDetail(ShoppingCartProduct(
                    action.product.id,
                    name = action.product.name,
                    price = action.product.price
                )))
            }
            is FeaturesCoordinatorAction.BuyProductFlow -> {
                productsCoordinator.trigger(ProductsCoordinatorAction.StartPurchaseProduct(ShoppingCartProduct(
                    action.product.id,
                    name = action.product.name,
                    price = action.product.price
                )))
            }

            is FeaturesCoordinatorAction.AuthenticateUserAndTriggerAction -> {
                authCoordinator.trigger(
                    StartLogin {
                        trigger(action)
                    }
                )
            }
        }
    }

    override fun setupNavigation(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController,
    ) {
        authCoordinator.setupNavigation(navGraphBuilder, navHostController)
        homeCoordinator.setupNavigation(navGraphBuilder, navHostController)
        productsCoordinator.setupNavigation(navGraphBuilder, navHostController)
    }
}
