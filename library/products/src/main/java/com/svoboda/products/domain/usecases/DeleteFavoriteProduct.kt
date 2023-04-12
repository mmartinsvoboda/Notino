package com.svoboda.products.domain.usecases

import com.svoboda.database.domain.repository.FavoriteProductRepository

class DeleteFavoriteProduct(private val repository: FavoriteProductRepository) {
    suspend operator fun invoke(productId: Int) = repository.deleteFavoriteProduct(productId)
}
