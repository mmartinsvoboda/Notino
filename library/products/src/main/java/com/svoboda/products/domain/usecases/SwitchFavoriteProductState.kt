package com.svoboda.products.domain.usecases

import com.svoboda.database.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.first

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
