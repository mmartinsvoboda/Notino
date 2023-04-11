package com.svoboda.products.data

import com.svoboda.products.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductsRepository(
    private val api: ProductsApi,
    private val dispatcher: CoroutineDispatcher
) : com.svoboda.products.domain.ProductsRepository {
    override suspend fun getProducts(): List<Product> = withContext(dispatcher) {
        val products = api.getProducts()

        products.products.map {
            it.toProduct()
        }
    }
}
