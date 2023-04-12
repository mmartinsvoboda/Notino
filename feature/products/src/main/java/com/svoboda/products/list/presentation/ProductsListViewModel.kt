package com.svoboda.products.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svoboda.products.domain.model.Product
import com.svoboda.products.domain.usecases.DeleteFavoriteProduct
import com.svoboda.products.domain.usecases.GetProducts
import com.svoboda.products.domain.usecases.ObserveFavoriteProduct
import com.svoboda.products.domain.usecases.SetFavoriteProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductsListViewModel(
    private val getProducts: GetProducts,
    private val observeFavoriteProduct: ObserveFavoriteProduct,
    private val setFavoriteProduct: SetFavoriteProduct,
    private val deleteFavoriteProduct: DeleteFavoriteProduct
) : ViewModel() {

    val products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            products.value = getProducts()
        }
    }
}
