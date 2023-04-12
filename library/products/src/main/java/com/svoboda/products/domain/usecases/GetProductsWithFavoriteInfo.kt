package com.svoboda.products.domain.usecases

import com.svoboda.products.domain.model.ProductWithFavoriteInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

/**
 * Use case that returns all the products
 */
class GetProductsWithFavoriteInfo(
    private val getProducts: GetProducts,
    private val observeAllFavoriteProducts: ObserveAllFavoriteProducts,
    private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): Flow<List<ProductWithFavoriteInfo>> = withContext(dispatcher) {
        val products = getProducts()

        observeAllFavoriteProducts()
            .distinctUntilChanged()
            .map { favorites ->
                products.map {
                    ProductWithFavoriteInfo(
                        isFavorite = favorites.contains(it.productId),
                        product = it
                    )
                }
            }
    }

}
