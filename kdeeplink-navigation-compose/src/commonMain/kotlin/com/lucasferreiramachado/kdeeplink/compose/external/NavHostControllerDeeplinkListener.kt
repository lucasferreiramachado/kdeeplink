package com.lucasferreiramachado.kdeeplink.compose.external

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavHostController
import androidx.navigation.NavUri
import com.lucasferreiramachado.kdeeplink.external.KDeeplinkExternalUriHandler

@Composable
public fun NavHostController.listenExternalDeeplink() {
    // The effect is produced only once, as `Unit` never changes
    DisposableEffect(Unit) {
        // Sets up the listener to call `NavController.navigate()`
        // for the composable that has a matching `navDeepLink` listed
        KDeeplinkExternalUriHandler.listener = { uri ->
            navigate(NavUri(uri))
        }
        // Removes the listener when the composable is no longer active
        onDispose {
            KDeeplinkExternalUriHandler.listener = null
        }
    }
}