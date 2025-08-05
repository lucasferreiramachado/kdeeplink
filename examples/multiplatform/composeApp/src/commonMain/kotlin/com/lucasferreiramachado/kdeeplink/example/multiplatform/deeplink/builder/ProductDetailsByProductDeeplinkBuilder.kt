package com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.builder

import com.lucasferreiramachado.kdeeplink.builder.KDeeplinkBuilder
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.AppDeeplink
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.builder.model.Product
import com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.route.AppDeeplinkRoute
import kotlinx.serialization.json.Json

// DEEPLINK_SCHEME://product/detail/{json}

class ProductDetailsByProductDeeplinkBuilder(
    val product: Product
): KDeeplinkBuilder {

    override val route: AppDeeplinkRoute = AppDeeplinkRoute.ProductDetailsByProduct

    override fun build(): String {
        val productString = Json.encodeToString(product)
        return createRoute(productString)
    }

    private fun createRoute(json: String): String {
        val deeplinkScheme = AppDeeplink.defaultScheme()
        return "${deeplinkScheme}feature/ProductList/${json}"
    }
}