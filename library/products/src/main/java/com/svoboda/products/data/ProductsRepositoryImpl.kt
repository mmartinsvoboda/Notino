package com.svoboda.products.data

import com.svoboda.products.domain.model.Product
import com.svoboda.products.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductsRepositoryImpl(
    private val api: ProductsApi,
    private val dispatcher: CoroutineDispatcher
) : ProductsRepository {
    override suspend fun getProducts(): List<Product> = withContext(dispatcher) {
        val products = try {
            api.getProducts()
        } catch (_: Throwable) {
            ProductsDTO(emptyList())
        }

        products.products.map {
            it.toProduct()
        }
    }
}
