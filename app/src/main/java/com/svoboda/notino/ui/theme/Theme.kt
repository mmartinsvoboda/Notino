package com.svoboda.notino.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = InkPrimary,
    primaryVariant = InkPrimary,
    secondary = InkSecondary,
    secondaryVariant = InkTertiary
)

private val LightColorPalette = lightColors(
    primary = InkPrimary,
    primaryVariant = InkPrimary,
    secondary = InkSecondary,
    secondaryVariant = InkTertiary
)

@Composable
fun NotinoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
