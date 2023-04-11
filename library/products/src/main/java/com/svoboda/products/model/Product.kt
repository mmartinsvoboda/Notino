package com.svoboda.products.model

/**
 * Domain model of product
 *
 * @property annotation Short description of the product
 * @property brandName Name of the brand
 * @property imageUrl Full url of the image
 * @property name Name of the product
 * @property orderUnit Unit in which the product can be ordered
 * @property price Price of the product, currency and value itself
 * @property productId Id of the product
 * @property reviewScore Review score of the product
 */
data class Product(
    val `annotation`: String,
    val brandName: String,
    val imageUrl: String,
    val name: String,
    val orderUnit: String,
    val price: Price,
    val productId: Int,
    val reviewScore: Int
)
