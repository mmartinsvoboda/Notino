package com.svoboda.products.domain.usecases

import com.svoboda.database.domain.repository.FavoriteProductRepository

/**
 * Use case removes favorite state of a product.
 */
class DeleteFavoriteProduct(private val repository: FavoriteProductRepository) {
    suspend operator fun invoke(productId: Int) = repository.deleteFavoriteProduct(productId)
}
