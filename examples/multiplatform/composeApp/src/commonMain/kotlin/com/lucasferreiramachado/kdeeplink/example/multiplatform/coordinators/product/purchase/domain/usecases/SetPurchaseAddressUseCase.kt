package com.lucasferreiramachado.kdeeplink.example.multiplatform.coordinators.product.purchase.domain.usecases

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.PurchaseRepository

class SetPurchaseAddressUseCase(
    val repository: PurchaseRepository
) {
    fun execute(address: String) = repository.setAddress(address)
}