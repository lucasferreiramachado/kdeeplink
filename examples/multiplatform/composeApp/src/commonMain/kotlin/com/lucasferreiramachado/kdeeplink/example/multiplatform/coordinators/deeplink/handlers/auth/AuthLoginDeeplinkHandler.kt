package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.handlers.auth

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import com.lucasferreiramachado.kdeeplink.compose.handler.KDeeplinkHandler
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.FeaturesCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.DeeplinkCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.deeplink.DeeplinkCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.route.AppDeeplinkRoute

class AuthLoginDeeplinkHandler(
    val coordinator: DeeplinkCoordinator
): KDeeplinkHandler {

    override val deeplink: AppDeeplinkRoute = AppDeeplinkRoute.AuthLoginFlow

    override val composable: @Composable (AnimatedContentScope.(NavBackStackEntry) -> Unit) = {
        LaunchedEffect(deeplink.route) {
            coordinator.handle(
                DeeplinkCoordinatorAction.ProcessDeeplink(
                    deeplink,
                    FeaturesCoordinatorAction.StartLoginFlow
                )
            )
        }
    }
}