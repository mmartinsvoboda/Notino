package com.svoboda.products.domain.repository

import com.svoboda.architecture.Result
import com.svoboda.products.domain.model.Product

interface ProductsRepository {

    /**
     * Returns all the products that should be displayed to the user.
     *
     * @return [Result] of [List] of [Product]
     */
    suspend fun getProducts(): Result<List<Product>>
}
