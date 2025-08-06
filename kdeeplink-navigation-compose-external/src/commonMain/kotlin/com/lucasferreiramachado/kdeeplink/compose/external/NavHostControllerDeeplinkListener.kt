package com.lucasferreiramachado.kdeeplink.compose.external

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavUri

@Composable
public fun NavHostController.listenExternalUriDeeplink() {
    DisposableEffect(Unit) {
        DeeplinkExternalUriHandler.listener = { uri ->
            navigate(NavUri(uri))
        }
        onDispose {
            DeeplinkExternalUriHandler.listener = null
        }
    }
}