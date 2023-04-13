package com.svoboda.products.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svoboda.products.domain.model.Product
import com.svoboda.products.domain.usecases.GetProducts
import com.svoboda.products.domain.usecases.ObserveAllFavoriteProducts
import com.svoboda.products.domain.usecases.SwitchFavoriteProductState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ProductsListViewModel(
    private val getProducts: GetProducts,
    private val observeAllFavoriteProducts: ObserveAllFavoriteProducts,
    private val switchFavoriteProductState: SwitchFavoriteProductState
) : ViewModel() {

    val products: MutableStateFlow<List<Product>> = MutableStateFlow(emptyList())
    private val favoriteProducts: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            products.value = getProducts()
            observeAllFavoriteProducts().collect {
                favoriteProducts.value = it
            }
        }
    }

    fun getFavoriteFlowOfProduct(productId: Int) = favoriteProducts.map { it.contains(productId) }

    fun changeProductFavoriteState(productId: Int) = viewModelScope.launch {
        switchFavoriteProductState(productId)
    }
}
