package com.svoboda.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.svoboda.ui.theme.LocalNotinoColors

/**
 * Composable for displaying a remote image using Coil library with a loading indicator.
 *
 * @param url [String] representing the URL of the image to load.
 * @param contentDescription [String] describing the content of the image for accessibility purposes.
 * @param modifier [Modifier] to be applied to the composable.
 * @param alignment [Alignment] for the image.
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun RemoteImage(
    url: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    alignment: Alignment
) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
        }
    )

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = ContentScale.Fit
        )

        if (painter.state is ImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .progressSemantics(),
                color = LocalNotinoColors.current.colors.primary
            )
        }
    }
}
