package com.lucasferreiramachado.kdeeplink.compose.handler

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.lucasferreiramachado.kdeeplink.compose.route.navDeeplinks
import com.lucasferreiramachado.kdeeplink.route.KDeeplinkRoute

interface KDeeplinkHandler {

    val deeplink: KDeeplinkRoute

    val composable: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit

    fun setupNavigation(navGraphBuilder: NavGraphBuilder) {
        navGraphBuilder.composable(
            route = deeplink.route,
            deepLinks = deeplink.navDeeplinks(),
            content = composable
        )
    }
}