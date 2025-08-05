package com.lucasferreiramachado.kdeeplink.example.multiplatform.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.app.AppCoordinator
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.app.AppCoordinatorAction
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.app.AppNavigationRoute

@Composable
fun App(
    startDestination: Any = AppNavigationRoute.SplashScreen,
    initialAction: AppCoordinatorAction = AppCoordinatorAction.StartLoginFlow,
) {
    MaterialTheme {
        val appCoordinator = AppCoordinator()
        appCoordinator.start(
            startDestination,
            initialAction = initialAction
        )
    }
}