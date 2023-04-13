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
import com.svoboda.ui.theme.LocalNotinoColors
import kotlinx.coroutines.flow.Flow

/**
 * Composable for displaying a favorite button with two states - favorite and not favorite.
 *
 * @param onClick A lambda function that is executed when the button is clicked.
 * @param isFavoriteFlow [Flow] of [Boolean] indicating the current favorite state.
 * @param modifier [Modifier] to be applied to the composable.
 * @param defaultColor [Color] to be used for the icon when not in the favorite state.
 * @param favoriteColor [Color] to be used for the icon when in the favorite state.
 */
@Composable
fun FavoriteButton(
    onClick: () -> Unit,
    isFavoriteFlow: Flow<Boolean>,
    modifier: Modifier = Modifier,
    defaultColor: Color = LocalNotinoColors.current.tertiary,
    favoriteColor: Color = LocalNotinoColors.current.pink
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
