package com.svoboda.products.list.presentation.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.svoboda.feature.products.R
import com.svoboda.products.domain.model.Price
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
    Column(modifier = Modifier.fillMaxWidth()) {
        FavoriteSection(
            onProductLikeClicked = onProductLikeClicked,
            productFavoriteStateFlow = productFavoriteStateFlow,
            productId = product.productId
        )

        ProductImage(imageUrl = product.imageUrl, contentDescription = product.name)

        ProductInformation(
            brandName = product.brandName,
            productName = product.name,
            productAnnotation = product.annotation
        )

        ProductRating(rating = product.reviewScore)

        ProductPrice(price = product.price)

        AddToCartButton()
    }
}

/**
 * Renders the favorite section in the product card, which includes a FavoriteButton.
 *
 * @param onProductLikeClicked A callback function that is triggered when the FavoriteButton is clicked.
 * @param productFavoriteStateFlow A Flow representing the favorite state of the product.
 * @param productId The id of the product to display.
 */
@Composable
private fun ColumnScope.FavoriteSection(
    onProductLikeClicked: (productId: Int) -> Unit,
    productFavoriteStateFlow: Flow<Boolean>,
    productId: Int
) {
    FavoriteButton(
        modifier = Modifier.align(Alignment.End),
        onClick = { onProductLikeClicked(productId) },
        isFavoriteFlow = productFavoriteStateFlow
    )
}

/**
 * Renders an image from a remote URL.
 *
 * @param imageUrl The URL of the image to display.
 * @param contentDescription The content description of the image.
 */
@Composable
private fun ProductImage(imageUrl: String, contentDescription: String) {
    RemoteImage(
        url = imageUrl,
        contentDescription = contentDescription,
        modifier = Modifier
            .height(160.dp)
            .fillMaxWidth(),
        alignment = Alignment.Center
    )
    Spacer(modifier = Modifier.height(12.dp))
}

/**
 * Renders the product information section, which includes the brand, product name, and annotation.
 *
 * @param brandName The brand of the product to display.
 * @param productName The name of the product to display.
 * @param productAnnotation The annotation of the product to display.
 */
@Composable
private fun ProductInformation(
    brandName: String,
    productName: String,
    productAnnotation: String
) {
    val notinoColors = LocalNotinoColors.current

    Text(
        text = brandName,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Typography.caption,
        color = notinoColors.tertiary
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = productName,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Typography.body2
    )
    Text(
        text = productAnnotation,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Typography.caption
    )
    Spacer(modifier = Modifier.height(12.dp))
}

/**
 * Renders the product rating section within the product card, which includes a rating bar and a spacer.
 *
 * @param rating The rating value for the product.
 */
@Composable
private fun ColumnScope.ProductRating(rating: Int) {
    RatingBar(
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .height(16.dp),
        rating = rating
    )
    Spacer(modifier = Modifier.height(12.dp))
}

/**
 * Renders the product price section, which displays the price and currency.
 *
 * @param price The price of the product to display.
 */
@Composable
private fun ProductPrice(price: Price) {
    Text(
        text = stringResource(R.string.price_from, price.value, price.currency),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = Typography.body2
    )
    Spacer(modifier = Modifier.height(12.dp))
}

/**
 * Renders the [NotinoButton], which toggles the product's presence in the cart.
 */
@Composable
private fun ColumnScope.AddToCartButton() {
    val notinoColors = LocalNotinoColors.current

    var isInCart by remember { mutableStateOf(Random.nextBoolean()) }

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

