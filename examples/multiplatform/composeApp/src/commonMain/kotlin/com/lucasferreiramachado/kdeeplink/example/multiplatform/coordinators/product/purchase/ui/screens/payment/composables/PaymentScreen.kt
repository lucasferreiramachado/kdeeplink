package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.ui.screens.payment.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.ui.screens.payment.PaymentViewModel

@Composable
fun PaymentScreen(
    viewModel: PaymentViewModel,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    PaymentView(
        state = state,
        onEvent = { event ->
            viewModel.onEvent(event)
        }
    )
}