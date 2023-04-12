package com.svoboda.database.data

import androidx.room.*
import com.svoboda.database.domain.model.FavoriteProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteProductDao {
    @Query("SELECT id FROM favorite_products")
    fun getAllFavoriteProductIds(): Flow<List<Int>>

    @Query("SELECT id FROM favorite_products WHERE id = :id")
    fun getProductById(id: Int): Flow<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(favoriteProduct: FavoriteProduct)

    @Delete
    fun delete(favoriteProduct: FavoriteProduct)
}
