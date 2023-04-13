package com.svoboda.products.data

import com.svoboda.architecture.Result
import com.svoboda.architecture.map
import com.svoboda.products.domain.model.Product
import com.svoboda.products.domain.repository.ProductsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductsRepositoryImpl(
    private val api: ProductsApi,
    private val dispatcher: CoroutineDispatcher
) : ProductsRepository {
    override suspend fun getProducts(): Result<List<Product>> =
        withContext(dispatcher) {
            val products = try {
                Result.success(api.getProducts())
            } catch (t: Throwable) {
                Result.error(t)
            }

            products.map { productsDTO ->
                productsDTO.products.map { productDTO ->
                    productDTO.toProduct()
                }
            }
        }
}
