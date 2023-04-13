package com.svoboda.products.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svoboda.architecture.UiState
import com.svoboda.architecture.extensions.toUiState
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

    val products: MutableStateFlow<UiState<List<Product>>> = MutableStateFlow(UiState.Loading)
    private val favoriteProducts: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())

    init {
        loadAllProducts()
        viewModelScope.launch {
            observeAllFavoriteProducts().collect {
                favoriteProducts.value = it
            }
        }
    }

    fun loadAllProducts() {
        viewModelScope.launch {
            products.value = UiState.Loading
            products.value = getProducts().toUiState()
        }
    }

    fun getFavoriteFlowOfProduct(productId: Int) = favoriteProducts.map { it.contains(productId) }

    fun changeProductFavoriteState(productId: Int) = viewModelScope.launch {
        switchFavoriteProductState(productId)
    }
}
