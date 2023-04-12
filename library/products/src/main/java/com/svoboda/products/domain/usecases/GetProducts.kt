package com.svoboda.products.domain.usecases

import com.svoboda.products.domain.repository.ProductsRepository

/**
 * Use case that returns all the products
 */
class GetProducts(private val repository: ProductsRepository) {

    suspend operator fun invoke() = repository.getProducts()

}
