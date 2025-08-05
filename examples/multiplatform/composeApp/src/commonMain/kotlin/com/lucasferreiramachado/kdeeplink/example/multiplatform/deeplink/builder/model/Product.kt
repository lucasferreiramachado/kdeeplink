package com.lucasferreiramachado.kdeeplink.example.multiplatform.deeplink.builder.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(val id: Int, val name: String, val price: Double)

//class ProductNavType : NavType<Product>(isNullableAllowed = false) {
//    override fun put(
//        bundle: SavedState,
//        key: String,
//        value: Product,
//    ) {
//        val json = Json.encodeToString(value)
//        bundle.write { this.putCharSequence(key, json) }
//    }
//
//    override fun get(
//        bundle: SavedState,
//        key: String,
//    ): Product? {
//        val value = bundle.read { getStringOrNull(key) }
//        if (value != null) {
//            return parseValue(value)
//        }
//        return null
//    }
//
//    override fun parseValue(value: String): Product {
//        return Json.decodeFromString<Product>(value)
//    }
//}