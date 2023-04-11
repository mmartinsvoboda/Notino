package com.svoboda.products.domain

import com.svoboda.products.model.Product

interface ProductsRepository {

    /**
     * Returns all the products that should be displayed to the user.
     *
     * @return [List] of [Product]
     */
    suspend fun getProducts(): List<Product>
}
