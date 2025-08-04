package com.lucasferreiramachado.kdeeplink.compose.route

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink
import com.lucasferreiramachado.kdeeplink.KDeeplink
import com.lucasferreiramachado.kdeeplink.route.KDeeplinkRoute

public fun KDeeplinkRoute.navDeeplinks(): List<NavDeepLink> {
    val uriPatterns = KDeeplink.schemes.map { "$it$route" }
    return uriPatterns.map { navDeepLink { uriPattern = it } }
}