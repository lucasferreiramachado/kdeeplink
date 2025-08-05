package com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink

public object AppDeeplink {

    var schemes: List<String> = listOf(
         "myapp:/", "myapp://",
    )

    fun defaultScheme(): String = schemes.first()
}