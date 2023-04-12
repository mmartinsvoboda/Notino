package com.svoboda.database.data

import com.svoboda.database.domain.model.FavoriteProduct
import com.svoboda.database.domain.repository.FavoriteProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FavoriteProductRepositoryImpl(
    private val dao: FavoriteProductDao,
    private val dispatcher: CoroutineDispatcher
    ) :
    FavoriteProductRepository {

    override fun observeAllFavoriteProducts(): Flow<List<Int>> =
        dao.getAllFavoriteProductIds()

    override fun observeFavoriteProducts(productId: Int): Flow<Int?> =
        dao.getProductById(productId)

    override suspend fun setFavoriteProduct(productId: Int) = withContext(dispatcher) {
        dao.insertOrUpdate(FavoriteProduct(productId))
    }

    override suspend fun deleteFavoriteProduct(productId: Int) = withContext(dispatcher) {
        dao.delete(FavoriteProduct(productId))
    }

}
