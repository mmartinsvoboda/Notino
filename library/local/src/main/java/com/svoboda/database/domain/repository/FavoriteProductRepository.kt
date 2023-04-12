package com.svoboda.database.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteProductRepository {

    // Observes favorite products
    fun observeFavoriteProducts(productId: Int): Flow<Int?>

    // Adds product to favorites
    suspend fun setFavoriteProduct(productId: Int)

    // Removes product from favorites
    suspend fun deleteFavoriteProduct(productId: Int)

}
