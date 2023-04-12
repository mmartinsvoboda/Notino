package com.svoboda.products.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svoboda.products.domain.model.ProductWithFavoriteInfo
import com.svoboda.products.domain.usecases.DeleteFavoriteProduct
import com.svoboda.products.domain.usecases.GetProductsWithFavoriteInfo
import com.svoboda.products.domain.usecases.ObserveFavoriteProduct
import com.svoboda.products.domain.usecases.SetFavoriteProduct
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProductsListViewModel(
    private val getProductsWithFavoriteInfo: GetProductsWithFavoriteInfo,
    private val observeFavoriteProduct: ObserveFavoriteProduct,
    private val setFavoriteProduct: SetFavoriteProduct,
    private val deleteFavoriteProduct: DeleteFavoriteProduct
) : ViewModel() {

    val products: MutableStateFlow<List<ProductWithFavoriteInfo>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            getProductsWithFavoriteInfo().collect {
                products.value = it
            }
        }
    }

    fun changeProductFavoriteState(productId: Int, isFavorite: Boolean) = viewModelScope.launch {
        if (isFavorite)
            deleteFavoriteProduct(productId)
        else
            setFavoriteProduct(productId)
    }
}
