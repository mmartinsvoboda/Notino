package com.svoboda.products.list.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.svoboda.feature.products.R
import com.svoboda.products.list.presentation.compose.ProductCard
import com.svoboda.ui.NotinoCenterAlignedTopAppBar
import com.svoboda.ui.UiStateContent
import com.svoboda.ui.theme.Typography

/**
 * Composable screen for displaying a list of products.
 *
 * @param viewModel [ProductsListViewModel] instance to provide data and handle events.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsListScreen(viewModel: ProductsListViewModel) {
    val uiStateState = viewModel.products.collectAsState()

    Scaffold(
        topBar = {
            NotinoCenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.products),
                        style = Typography.body1
                    )
                }
            )
        }
    ) { paddingValues ->
        UiStateContent(
            uiState = uiStateState.value,
            onNonSuccessStateButtonClicked = viewModel::loadAllProducts,
            onSuccess = { productList ->
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize(),
                    columns = GridCells.Adaptive(minSize = 164.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    items(
                        items = productList,
                        key = { it.productId }
                    ) { product ->
                        ProductCard(
                            product = product,
                            onProductLikeClicked = viewModel::changeProductFavoriteState,
                            productFavoriteStateFlow = viewModel.getFavoriteFlowOfProduct(product.productId)
                        )
                    }
                }
            }
        )
    }
}
