package com.lucasferreiramachado.kdeeplink.compose.external

public object DeeplinkExternalUriHandler {
    private var cached: String? = null

    public var listener: ((uri: String) -> Unit)? = null
        set(value) {
            field = value
            if (value != null) {
                cached?.let { value.invoke(it) }
                cached = null
            }
        }

    public fun onNewUri(uri: String) {
        cached = uri
        listener?.let {
            it.invoke(uri)
            cached = null
        }
    }
}