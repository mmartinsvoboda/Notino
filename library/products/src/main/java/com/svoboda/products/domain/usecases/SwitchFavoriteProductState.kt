package com.svoboda.products.domain.usecases

import kotlinx.coroutines.flow.first

/**
 * Use case switches favorite state of a product.
 */
class SwitchFavoriteProductState(
    private val observeFavoriteProduct: ObserveFavoriteProduct,
    private val setFavoriteProduct: SetFavoriteProduct,
    private val deleteFavoriteProduct: DeleteFavoriteProduct
) {
    suspend operator fun invoke(productId: Int) {
        val isFavorite = observeFavoriteProduct(productId).first() != null
        if (isFavorite) {
            deleteFavoriteProduct(productId)
        } else {
            setFavoriteProduct(productId)
        }
    }
}
