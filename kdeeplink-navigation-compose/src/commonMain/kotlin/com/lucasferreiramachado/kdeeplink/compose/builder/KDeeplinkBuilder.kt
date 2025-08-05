package com.lucasferreiramachado.kdeeplink.compose.builder

import androidx.navigation.NavHostController
import androidx.navigation.NavUri
import com.lucasferreiramachado.kdeeplink.KDeeplink
import com.lucasferreiramachado.kdeeplink.builder.KDeeplinkBuilder

public fun KDeeplinkBuilder.deeplinkScheme(): String = KDeeplink.schemes.first()

public fun NavHostController.navigateTo(builder: KDeeplinkBuilder) {
    val uriString = builder.build()
    navigate(NavUri(uriString))
}