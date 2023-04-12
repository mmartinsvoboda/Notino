package com.svoboda.products

import com.svoboda.database.data.FavoriteProductRepositoryImpl
import com.svoboda.database.domain.repository.FavoriteProductRepository
import com.svoboda.products.data.ProductsApi
import com.svoboda.products.data.ProductsRepositoryImpl
import com.svoboda.products.domain.usecases.GetProducts
import com.svoboda.products.domain.repository.ProductsRepository
import com.svoboda.products.domain.usecases.DeleteFavoriteProduct
import com.svoboda.products.domain.usecases.ObserveFavoriteProduct
import com.svoboda.products.domain.usecases.SetFavoriteProduct
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit

val libProductsModule = module {
    factory { provideProductsApi(get()) }
    factory<ProductsRepository> {
        ProductsRepositoryImpl(
            api = get(),
            dispatcher = Dispatchers.IO
        )
    }

    factory<FavoriteProductRepository> {
        FavoriteProductRepositoryImpl(get())
    }

    factory { GetProducts(get()) }
    factory { ObserveFavoriteProduct(get()) }
    factory { SetFavoriteProduct(get()) }
    factory { DeleteFavoriteProduct(get()) }
}

fun provideProductsApi(retrofit: Retrofit): ProductsApi = retrofit.create(ProductsApi::class.java)
