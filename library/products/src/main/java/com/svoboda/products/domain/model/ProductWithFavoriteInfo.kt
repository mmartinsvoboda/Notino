package com.svoboda.products.domain.model

/**
 * Domain model of product with favorite info
 *
 * @property isFavorite Whether user marked the item as favorite
 * @property product Product model
 */
data class ProductWithFavoriteInfo(
    val isFavorite: Boolean,
    val product: Product
)
