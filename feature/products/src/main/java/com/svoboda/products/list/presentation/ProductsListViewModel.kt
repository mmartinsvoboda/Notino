package com.svoboda.products.list.presentation

import androidx.lifecycle.ViewModel
import com.svoboda.products.domain.usecases.DeleteFavoriteProduct
import com.svoboda.products.domain.usecases.GetProducts
import com.svoboda.products.domain.usecases.ObserveFavoriteProduct
import com.svoboda.products.domain.usecases.SetFavoriteProduct

class ProductsListViewModel(
    private val getProducts: GetProducts,
    private val observeFavoriteProduct: ObserveFavoriteProduct,
    private val setFavoriteProduct: SetFavoriteProduct,
    private val deleteFavoriteProduct: DeleteFavoriteProduct
) : ViewModel() {

}
