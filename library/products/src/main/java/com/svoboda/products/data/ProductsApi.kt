package com.svoboda.products.data

import retrofit2.Call
import retrofit2.http.GET

/**
 * Yoga module related APIs.
 */
interface ProductsApi {

    /**
     * Returns yoga module detail
     */
    @GET("cernfr1993/notino-assignment/db")
    suspend fun getProducts(): ProductsDTO
}
