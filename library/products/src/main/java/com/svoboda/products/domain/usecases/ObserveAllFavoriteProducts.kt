package com.svoboda.products.domain.usecases

import com.svoboda.database.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case observes favorite state of all products.
 */
class ObserveAllFavoriteProducts(private val repository: FavoriteProductRepository) {
    operator fun invoke(): Flow<List<Int>> = repository.observeAllFavoriteProducts()
}
