package com.svoboda.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class NotinoColors(
    val colors: Colors,
    val tertiary: Color,
    val pink: Color,
    val gray: Color,
    val grayLight: Color
)

val LocalNotinoColors = staticCompositionLocalOf<NotinoColors> {
    error("No NotinoColors provided")
}

@Composable
fun NotinoColors(
    colors: Colors,
    content: @Composable () -> Unit
) {
    val notinoColors = NotinoColors(
        colors = colors,
        tertiary = InkTertiary,
        pink = Pink,
        gray = Gray,
        grayLight = GrayLight
    )

    CompositionLocalProvider(LocalNotinoColors provides notinoColors) {
        content()
    }
}
