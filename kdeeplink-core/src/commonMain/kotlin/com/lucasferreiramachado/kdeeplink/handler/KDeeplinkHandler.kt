package com.lucasferreiramachado.kdeeplink.handler

import com.lucasferreiramachado.kdeeplink.route.KDeeplinkRoute

public interface KDeeplinkHandler {
    public fun canHandle(route: KDeeplinkRoute): Boolean
    public fun handle(route: KDeeplinkRoute)
}