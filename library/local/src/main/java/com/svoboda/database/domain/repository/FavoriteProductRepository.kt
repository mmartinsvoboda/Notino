package com.svoboda.database.domain.repository

import kotlinx.coroutines.flow.Flow

interface FavoriteProductRepository {

    // Observes all favorite products
    fun observeAllFavoriteProducts(): Flow<List<Int>>

    // Observes favorite product by id
    fun observeFavoriteProducts(productId: Int): Flow<Int?>

    // Adds product to favorites
    suspend fun setFavoriteProduct(productId: Int)

    // Removes product from favorites
    suspend fun deleteFavoriteProduct(productId: Int)

}
