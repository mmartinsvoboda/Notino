package com.svoboda.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.svoboda.ui.theme.LocalNotinoColors

/**
 * Composable for displaying a rating bar with filled and unfilled stars.
 *
 * @param modifier [Modifier] to be applied to the composable.
 * @param rating [Int] number of filled stars to display, default value is 0.
 * @param stars [Int] total number of stars in the rating bar, default value is 5.
 * @param emptyStarsColor [Color] to be used for unfilled stars, default is #EEEEEE.
 * @param starsColor [Color] to be used for filled stars, default is #333333.
 */
@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Int = 0,
    stars: Int = 5,
    emptyStarsColor: Color = LocalNotinoColors.current.grayLight,
    starsColor: Color = LocalNotinoColors.current.colors.secondary,
) {
    val unfilledStars = stars - rating
    Row(modifier = modifier) {
        repeat(rating) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor)
        }
        repeat(unfilledStars) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = emptyStarsColor
            )
        }
    }
}
