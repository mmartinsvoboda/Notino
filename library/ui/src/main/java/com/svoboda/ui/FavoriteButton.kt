package com.svoboda.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow

@Composable
fun FavoriteButton(
    onClick: () -> Unit,
    isFavoriteFlow: Flow<Boolean>,
    modifier: Modifier = Modifier,
    defaultColor: Color,
    favoriteColor: Color
) {
    val isFavorite by isFavoriteFlow.collectAsState(initial = false)

    IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier
    ) {
        Crossfade(
            targetState = isFavorite,
            animationSpec = tween(durationMillis = 500)
        ) { showFilled ->
            if (showFilled) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = favoriteColor
                )
            } else {
                Icon(
                    imageVector = Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = defaultColor
                )
            }
        }
    }
}
