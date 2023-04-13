package com.svoboda.products.list.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.svoboda.architecture.UiState
import com.svoboda.products.domain.model.Product
import com.svoboda.products.list.presentation.compose.ProductCard
import com.svoboda.ui.NotinoButton
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
        when (products.value) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = LocalNotinoColors.current.colors.primary
                    )
                }
            }
            is UiState.Success -> {
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
                        items = (products.value as UiState.Success<List<Product>>).value,
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
            is UiState.SuccessEmpty,
            is UiState.Error,
            is UiState.Failure -> {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Omlouváme se, něco se pokazilo.")
                    Spacer(modifier = Modifier.height(16.dp))
                    NotinoButton(onClick = viewModel::loadAllProducts) {
                        Text(text = "Zkusit znovu")
                    }
                }
            }
        }
    }
}
