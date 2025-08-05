package com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase

import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.PaymentMethod
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.Purchase
import com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model.ShoppingCartProduct

interface PurchaseRepository {
    fun startNewPurchase(product: ShoppingCartProduct)
    fun setAddress(address: String)
    fun setPaymentMethod(paymentMethod: PaymentMethod)
    fun getCurrentPurchase(): Purchase?
}