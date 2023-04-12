package com.svoboda.products.list.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.svoboda.products.domain.model.Product
import com.svoboda.ui.FavoriteButton
import com.svoboda.ui.RatingBar
import com.svoboda.ui.RemoteImage
import com.svoboda.ui.theme.LocalNotinoColors
import com.svoboda.ui.theme.NotinoColors
import kotlin.random.Random

@Composable
fun ProductCard(product: Product) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        FavoriteButton(
            modifier = Modifier.align(Alignment.End),
            onClick = { /*TODO*/ },
            isFavorite = Random.nextBoolean(),
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
        Text(
            text = product.brandName,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = product.name,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = product.annotation,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        RatingBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            rating = product.reviewScore
        )
        Text(
            text = "od ${product.price.value} ${product.price.currency}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Do košíku")
        }
    }
}
