package com.svoboda.products

import com.svoboda.products.data.ProductsApi
import com.svoboda.products.domain.GetProducts
import com.svoboda.products.domain.ProductsRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit

val productsModule = module {
    factory { provideProductsApi(get()) }
    factory<ProductsRepository> {
        com.svoboda.products.data.ProductsRepository(
            api = get(),
            dispatcher = Dispatchers.IO
        )
    }
    factory { GetProducts(get()) }
}

fun provideProductsApi(retrofit: Retrofit): ProductsApi = retrofit.create(ProductsApi::class.java)
