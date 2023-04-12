package com.svoboda.products.data

import com.svoboda.products.domain.repository.ProductsRepository
import com.svoboda.products.domain.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductsRepositoryImpl(
    private val api: ProductsApi,
    private val dispatcher: CoroutineDispatcher
) : ProductsRepository {
    override suspend fun getProducts(): List<Product> = withContext(dispatcher) {
        val products = api.getProducts()

        products.products.map {
            it.toProduct()
        }
    }
}
