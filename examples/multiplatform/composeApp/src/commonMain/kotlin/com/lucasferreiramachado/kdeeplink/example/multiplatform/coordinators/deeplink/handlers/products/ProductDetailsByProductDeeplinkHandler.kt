package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.handlers.products

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.savedstate.SavedState
import androidx.savedstate.read
import com.lucasferreiramachado.kdeeplink.compose.handler.KDeeplinkHandler
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.builder.model.Product
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.route.AppDeeplinkRoute
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.FeaturesCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.FeaturesCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.DeeplinkCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.DeeplinkCoordinatorAction
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

class ProductDetailsByProductDeeplinkHandler(
    val coordinator: DeeplinkCoordinator
): KDeeplinkHandler {

    override val deeplink: AppDeeplinkRoute = AppDeeplinkRoute.ProductList

    override val composable: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit) = {
        LaunchedEffect(deeplink.route) {

            decodeParam(it.arguments) { param ->

                coordinator.handle(
                    DeeplinkCoordinatorAction.ProcessDeeplink(
                        deeplink,
                        FeaturesCoordinatorAction.ProductDetailFlow(param)
                    )
                )
            }
        }
    }

    private fun decodeParam(from: SavedState?, onSuccess: (Product)-> Unit) {
        val json = from?.read { getStringOrNull(deeplink.paramName) }
        json?.let {
            var product: Product? = null
            try {
                product = Json.decodeFromString<Product>(json)
            } catch (serialization: SerializationException) {
            } catch (illegalArgument: IllegalArgumentException) {
            }

            product?.let {
                onSuccess(it)
            }
        }
    }
}