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
import com.svoboda.products.domain.model.Product
import com.svoboda.ui.FavoriteButton
import com.svoboda.ui.NotinoButton
import com.svoboda.ui.RatingBar
import com.svoboda.ui.RemoteImage
import com.svoboda.ui.theme.LocalNotinoColors
import com.svoboda.ui.theme.Typography
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

@Composable
fun ProductCard(
    product: Product,
    onProductLikeClicked: (productId: Int) -> Unit,
    productFavoriteStateFlow: Flow<Boolean>
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FavoriteButton(
            modifier = Modifier.align(Alignment.End),
            onClick = {
                onProductLikeClicked(product.productId)
            },
            isFavoriteFlow = productFavoriteStateFlow,
            defaultColor = LocalNotinoColors.current.tertiary,
            favoriteColor = LocalNotinoColors.current.pink
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
            color = LocalNotinoColors.current.tertiary
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
            text = "od ${product.price.value} ${product.price.currency}",
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
