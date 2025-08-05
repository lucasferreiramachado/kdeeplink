package com.lucasferreiramachado.kdeeplink.example.multiplatform.data.purchase.model


data class Purchase(
    var purchasedProduct: ShoppingCartProduct,
    var address: String? = null,
    var paymentMethod: PaymentMethod? = null,
)