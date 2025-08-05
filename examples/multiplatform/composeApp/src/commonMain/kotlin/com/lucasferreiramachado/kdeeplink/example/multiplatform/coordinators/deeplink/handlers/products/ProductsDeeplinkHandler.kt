package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.handlers.products

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kdeeplink.compose.handler.KDeeplinkHandler
import com.lucasferreiramachado.kdeeplink.compose.route.navDeeplinks
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.route.AppDeeplinkRoute
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.FeaturesCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.FeaturesCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.DeeplinkCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.DeeplinkCoordinatorAction
import kotlinx.coroutines.flow.MutableStateFlow

class ProductsDeeplinkHandler(
    val coordinator: DeeplinkCoordinator
): KDeeplinkHandler {

    override val deeplink: AppDeeplinkRoute = AppDeeplinkRoute.ProductList

    override val composable: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit) = {
        LaunchedEffect(deeplink.route) {
            coordinator.handle(
                DeeplinkCoordinatorAction.ProcessDeeplink(
                    deeplink,
                    FeaturesCoordinatorAction.StartProductListFlow
                )
            )
        }
    }
}