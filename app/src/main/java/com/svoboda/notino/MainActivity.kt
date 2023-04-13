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

/**
 * The main activity of the application, responsible for hosting the [ProductsListScreen].
 * It sets up the theme and colors for the application, and provides the [ProductsListViewModel].
 */
class MainActivity : ComponentActivity() {

    private val viewModel: ProductsListViewModel by viewModel()

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     * being shut down, then this Bundle contains the data it most recently supplied in
     * [onSaveInstanceState]. Otherwise, it is null.
     */
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
