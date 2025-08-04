package com.lucasferreiramachado.kdeeplink.compose.builder

import com.lucasferreiramachado.kdeeplink.KDeeplink
import com.lucasferreiramachado.kdeeplink.builder.KDeeplinkBuilder

public fun KDeeplinkBuilder.deeplinkScheme(): String = KDeeplink.schemes.first()