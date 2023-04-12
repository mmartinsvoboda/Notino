package com.svoboda.database.data

import com.svoboda.database.domain.model.FavoriteProduct
import com.svoboda.database.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.flow.Flow

class FavoriteProductRepositoryImpl(private val dao: FavoriteProductDao) :
    FavoriteProductRepository {

    override fun observeFavoriteProducts(productId: Int): Flow<Int?> {
        return dao.getProductById(productId)
    }

    override suspend fun setFavoriteProduct(productId: Int) {
        dao.insertOrUpdate(FavoriteProduct(productId))
    }

    override suspend fun deleteFavoriteProduct(productId: Int) {
        dao.delete(FavoriteProduct(productId))
    }

}
