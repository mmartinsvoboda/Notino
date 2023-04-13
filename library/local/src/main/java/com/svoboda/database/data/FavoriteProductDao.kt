package com.svoboda.database.data

import androidx.room.*
import com.svoboda.database.domain.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for favorite products.
 *
 * Provides methods to perform CRUD operations on favorite products data in the local database.
 */
@Dao
interface FavoriteProductDao {

    /**
     * Retrieves all favorite product IDs from the local database.
     *
     * @return [Flow] containing a [List] of favorite product IDs.
     */
    @Query("SELECT id FROM favorite_products")
    fun getAllFavoriteProductIds(): Flow<List<Int>>

    /**
     * Retrieves a specific favorite product by its ID from the local database.
     *
     * @param id The ID of the product to be retrieved.
     * @return [Flow] containing the product ID if found, or null otherwise.
     */
    @Query("SELECT id FROM favorite_products WHERE id = :id")
    fun getProductById(id: Int): Flow<Int?>

    /**
     * Inserts or updates a favorite product in the local database.
     *
     * @param favoriteProduct The [FavoriteProduct] to be inserted or updated.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(favoriteProduct: FavoriteProduct)

    /**
     * Deletes a favorite product from the local database.
     *
     * @param favoriteProduct The [FavoriteProduct] to be deleted.
     */
    @Delete
    fun delete(favoriteProduct: FavoriteProduct)
}

