package com.svoboda.notino

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.svoboda.products.list.presentation.ProductsListScreen
import com.svoboda.products.list.presentation.ProductsListViewModel
import com.svoboda.ui.theme.NotinoColors
import com.svoboda.ui.theme.NotinoTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: ProductsListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotinoTheme {
                NotinoColors(colors = MaterialTheme.colors) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ProductsListScreen(viewModel = viewModel)
                    }
                }
            }
        }
    }
}
