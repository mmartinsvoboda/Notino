package com.svoboda.products

import com.svoboda.products.list.presentation.ProductsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsModule = libProductsModule + module {
    viewModel { ProductsListViewModel(get(), get(), get(), get()) }
}
