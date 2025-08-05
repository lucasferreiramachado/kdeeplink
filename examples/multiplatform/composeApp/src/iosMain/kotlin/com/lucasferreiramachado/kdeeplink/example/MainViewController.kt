package com.lucasferreiramachado.kdeeplink.example

import androidx.compose.ui.window.ComposeUIViewController
import com.lucasferreiramachado.kdeeplink.example.multiplatform.app.App

fun MainViewController() = ComposeUIViewController { App() }