package com.svoboda.ui

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteButton(
    onClick: () -> Unit,
    isFavorite: Boolean,
    modifier: Modifier = Modifier,
    defaultColor: Color,
    favoriteColor: Color
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        if (isFavorite)
            Icon(Icons.Default.Favorite, null, tint = favoriteColor)
        else
            Icon(Icons.Outlined.FavoriteBorder, null, tint = defaultColor)
    }
}
