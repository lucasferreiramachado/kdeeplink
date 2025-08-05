package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.detail.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.list.ui.screens.detail.DetailViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    DetailView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}