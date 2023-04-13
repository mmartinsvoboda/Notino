package com.svoboda.products.list.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.svoboda.products.list.presentation.compose.ProductCard
import com.svoboda.ui.theme.LocalNotinoColors
import com.svoboda.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsListScreen(viewModel: ProductsListViewModel) {
    val products = viewModel.products.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Produkty",
                        style = Typography.body1
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = LocalNotinoColors.current.colors.surface,
                    titleContentColor = LocalNotinoColors.current.colors.onSurface
                ),
                modifier = Modifier.shadow(4.dp)
            )
        }
    ) { paddingValues ->
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
                items = products.value,
                key = {
                    it.productId
                }
            ) { product ->
                ProductCard(
                    product = product,
                    onProductLikeClicked = viewModel::changeProductFavoriteState,
                    productFavoriteStateFlow = viewModel.getFavoriteFlowOfProduct(product.productId)
                )
            }
        }

    }
}
