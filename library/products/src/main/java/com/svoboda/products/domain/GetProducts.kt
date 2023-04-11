package com.svoboda.products.domain

/**
 * Use case that returns all the products
 */
class GetProducts(private val repository: ProductsRepository) {

    suspend operator fun invoke() = repository.getProducts()

}
