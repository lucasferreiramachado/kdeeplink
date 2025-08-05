package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.domain.usecases

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.PurchaseRepository
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.ShoppingCartProduct

class StarNewPurchaseUseCase(
    val repository: PurchaseRepository
) {
    fun execute(purchasedProduct: ShoppingCartProduct) = repository.startNewPurchase(purchasedProduct)
}