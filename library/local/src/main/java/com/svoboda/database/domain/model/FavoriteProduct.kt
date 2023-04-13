package com.svoboda.database.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Favorite product representation. All we save is the product id, which can be matched to specific product.
 *
 * @property id Product id.
 */
@Entity(tableName = "favorite_products")
data class FavoriteProduct(
    @PrimaryKey
    val id: Int
)
