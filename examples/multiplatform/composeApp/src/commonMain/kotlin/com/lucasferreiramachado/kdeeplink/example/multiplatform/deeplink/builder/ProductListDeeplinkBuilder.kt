package com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.builder

import com.lucasferreiramachado.kdeeplink.builder.KDeeplinkBuilder
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.AppDeeplink
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.route.AppDeeplinkRoute
import com.lucasferreiramachado.kdeeplink.route.KDeeplinkRoute

// DEEPLINK_SCHEME://products

class ProductListDeeplinkBuilder: KDeeplinkBuilder {

    override val route: KDeeplinkRoute = AppDeeplinkRoute.ProductList

    override fun build(): String {
        return createRoute()
    }

    private fun createRoute(): String {
        val deeplinkScheme = AppDeeplink.defaultScheme()
        return "${deeplinkScheme}${route.route}"
    }
}