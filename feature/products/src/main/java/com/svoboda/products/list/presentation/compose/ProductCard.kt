package com.svoboda.products.list.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.svoboda.feature.products.R
import com.svoboda.products.domain.model.Product
import com.svoboda.ui.FavoriteButton
import com.svoboda.ui.NotinoButton
import com.svoboda.ui.RatingBar
import com.svoboda.ui.RemoteImage
import com.svoboda.ui.theme.LocalNotinoColors
import com.svoboda.ui.theme.Typography
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

/**
 * Composable card for displaying product information.
 *
 * @param product [Product] instance representing the product to display.
 * @param onProductLikeClicked Callback to handle the event when the favorite button is clicked.
 * @param productFavoriteStateFlow [Flow] of [Boolean] representing the favorite state of the product.
 */
@Composable
fun ProductCard(
    product: Product,
    onProductLikeClicked: (productId: Int) -> Unit,
    productFavoriteStateFlow: Flow<Boolean>
) {
    val notinoColors = LocalNotinoColors.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FavoriteButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { onProductLikeClicked(product.productId) },
            isFavoriteFlow = productFavoriteStateFlow
        )

        RemoteImage(
            url = product.imageUrl,
            contentDescription = product.name,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = product.brandName,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.caption,
            color = notinoColors.tertiary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = product.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.body2
        )

        Text(
            text = product.annotation,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))

        RatingBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(16.dp),
            rating = product.reviewScore
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = stringResource(R.string.price_from, product.price.value, product.price.currency),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.body2
        )

        Spacer(modifier = Modifier.height(12.dp))

        var isInCart by remember {
            mutableStateOf(Random.nextBoolean())
        }
        NotinoButton(
            onClick = { isInCart = !isInCart },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = if (isInCart) stringResource(R.string.in_cart) else stringResource(R.string.add_to_cart),
                style = Typography.body2,
                color = if (isInCart) notinoColors.tertiary else notinoColors.colors.primary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}
