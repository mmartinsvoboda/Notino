package com.svoboda.products.list.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.svoboda.products.domain.model.ProductWithFavoriteInfo
import com.svoboda.ui.FavoriteButton
import com.svoboda.ui.NotinoButton
import com.svoboda.ui.RatingBar
import com.svoboda.ui.RemoteImage
import com.svoboda.ui.theme.LocalNotinoColors
import com.svoboda.ui.theme.Typography
import kotlin.random.Random

@Composable
fun ProductCard(
    productWithFavoriteInfo: ProductWithFavoriteInfo,
    onProductLikeClicked: (productId: Int, isFavorite: Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FavoriteButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                onProductLikeClicked(
                    productWithFavoriteInfo.product.productId,
                    productWithFavoriteInfo.isFavorite
                )
            },
            isFavorite = productWithFavoriteInfo.isFavorite,
            defaultColor = LocalNotinoColors.current.tertiary,
            favoriteColor = LocalNotinoColors.current.pink
        )

        RemoteImage(
            url = productWithFavoriteInfo.product.imageUrl,
            contentDescription = productWithFavoriteInfo.product.name,
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            alignment = Alignment.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = productWithFavoriteInfo.product.brandName,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.caption,
            color = LocalNotinoColors.current.tertiary
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = productWithFavoriteInfo.product.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.body2
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = productWithFavoriteInfo.product.annotation,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.caption
        )

        Spacer(modifier = Modifier.height(12.dp))

        RatingBar(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(16.dp),
            rating = productWithFavoriteInfo.product.reviewScore
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "od ${productWithFavoriteInfo.product.price.value} ${productWithFavoriteInfo.product.price.currency}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = Typography.body2
        )

        Spacer(modifier = Modifier.height(12.dp))

        NotinoButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            val isInCart by remember {
                mutableStateOf(Random.nextBoolean())
            }

            Text(
                text = if (isInCart) "V košíku" else "Do košíku",
                style = Typography.body2,
                color = if (isInCart) LocalNotinoColors.current.tertiary else LocalNotinoColors.current.colors.primary
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
    }
}
