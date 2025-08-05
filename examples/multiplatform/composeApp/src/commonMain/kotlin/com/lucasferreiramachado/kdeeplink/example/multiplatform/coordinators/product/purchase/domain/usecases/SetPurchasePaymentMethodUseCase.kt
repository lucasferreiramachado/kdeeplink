package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.domain.usecases

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.PaymentMethod

class SetPurchasePaymentMethodUseCase(
    val repository: PurchaseRepository
) {
    fun execute(paymentMethod: PaymentMethod) = repository.setPaymentMethod(paymentMethod)
}