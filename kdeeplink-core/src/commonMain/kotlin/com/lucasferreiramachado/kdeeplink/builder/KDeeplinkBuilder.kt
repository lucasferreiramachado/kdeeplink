package com.lucasferreiramachado.kdeeplink.builder

import com.lucasferreiramachado.kdeeplink.route.KDeeplinkRoute

public interface KDeeplinkBuilder {

    public val route: KDeeplinkRoute

    public fun build(): String
}