package com.svoboda.products

import com.svoboda.architecture.Result
import com.svoboda.products.data.ProductsApi
import com.svoboda.products.data.ProductsDTO
import com.svoboda.products.data.ProductsRepositoryImpl
import com.svoboda.products.domain.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class ProductsRepositoryImplTest {
    private lateinit var api: ProductsApi
    private lateinit var productsRepository: ProductsRepositoryImpl

    @Before
    fun setUp() {
        api = Mockito.mock(ProductsApi::class.java)
        productsRepository = ProductsRepositoryImpl(api, Dispatchers.IO)
    }

    @Test
    fun `getProducts success`() = runTest {
        // Prepare mock data
        val productsDTO = ProductsDTO(listOf())
        val expectedProducts = listOf<Product>()

        // Mock the API call
        `when`(api.getProducts()).thenReturn(productsDTO)

        // Call the function
        val result = productsRepository.getProducts()

        // Verify the results
        assertEquals(Result.success(expectedProducts), result)
    }

    @Test
    fun `getProducts error`() = runTest {
        // Prepare error
        val throwable = RuntimeException("An error occurred")

        // Mock the API call
        `when`(api.getProducts()).thenThrow(throwable)

        // Call the function
        val result = productsRepository.getProducts()

        // Verify the results
        assertEquals(Result.error<List<Product>>(throwable), result)
    }
}

