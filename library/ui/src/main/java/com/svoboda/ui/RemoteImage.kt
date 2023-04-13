package com.svoboda.ui

import androidx.compose.foundation.progressSemantics
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.SubcomposeAsyncImage
import com.svoboda.ui.theme.LocalNotinoColors

/**
 * Composable for displaying a remote image using Coil library with a loading indicator.
 *
 * @param url [String] representing the URL of the image to load.
 * @param contentDescription [String] describing the content of the image for accessibility purposes.
 * @param modifier [Modifier] to be applied to the composable.
 * @param alignment [Alignment] for the image.
 */
@Composable
fun RemoteImage(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    alignment: Alignment
) {
    SubcomposeAsyncImage(
        model = url,
        loading = {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .progressSemantics(),
                color = LocalNotinoColors.current.colors.primary
            )
        },
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit,
        modifier = modifier,
        alignment = alignment
    )
}
