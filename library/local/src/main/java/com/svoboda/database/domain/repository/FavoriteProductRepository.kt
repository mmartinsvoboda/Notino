package com.svoboda.database.domain.repository

import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing favorite products, providing methods for observing, adding, and removing favorite products.
 */
interface FavoriteProductRepository {

    /**
     * Observes a list of IDs of all favorite products.
     *
     * @return A [Flow] containing a list of favorite product IDs.
     */
    fun observeAllFavoriteProducts(): Flow<List<Int>>

    /**
     * Observes a specific favorite product by its ID.
     *
     * @param productId The ID of the product to observe.
     * @return A [Flow] containing the favorite product ID if it exists, or `null` if it doesn't.
     */
    fun observeFavoriteProducts(productId: Int): Flow<Int?>

    /**
     * Adds a product to the list of favorites.
     *
     * @param productId The ID of the product to add to the favorites.
     */
    suspend fun setFavoriteProduct(productId: Int)

    /**
     * Removes a product from the list of favorites.
     *
     * @param productId The ID of the product to remove from the favorites.
     */
    suspend fun deleteFavoriteProduct(productId: Int)

}

