package com.svoboda.products.domain.usecases

import com.svoboda.database.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow

class ObserveFavoriteProduct(private val repository: FavoriteProductRepository) {
    operator fun invoke(productId: Int): Flow<Int?> = repository.observeFavoriteProducts(productId)
}
