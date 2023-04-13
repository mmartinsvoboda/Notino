package com.svoboda.products.domain.usecases

import com.svoboda.database.domain.repository.FavoriteProductRepository

/**
 * Use case sets favorite state of a product to true.
 */
class SetFavoriteProduct(private val repository: FavoriteProductRepository) {
    suspend operator fun invoke(productId: Int) = repository.setFavoriteProduct(productId)
}
