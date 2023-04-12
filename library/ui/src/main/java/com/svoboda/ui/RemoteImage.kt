package com.svoboda.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter

@Composable
fun RemoteImage(url: String, contentDescription: String) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true) // Pokud chcete přechodový efekt
            // Přidejte další konfigurace dle potřeby, např. zástupný obrázek, atd.
        }
    )

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = Modifier
        // Přidejte další modifikátory dle potřeby, např. velikost, zarovnání, atd.
    )
}
