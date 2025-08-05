package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.domain.usecases

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.Purchase

class GetCurrentPurchaseUseCase(
    val repository: PurchaseRepository
) {
    fun execute(): Purchase? = repository.getCurrentPurchase()
}