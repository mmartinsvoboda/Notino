package com.svoboda.products.data

import com.svoboda.products.model.Price
import com.svoboda.products.model.Product

private const val IMAGE_URL = "https://i.notino.com/detail_zoom/"

/**
 * Convert [ProductDTO] to [Product].
 */
fun ProductDTO.toProduct(): Product = Product(
    annotation = annotation,
    brandName = brand.name,
    imageUrl = "$IMAGE_URL$imageUrl",
    name = name,
    orderUnit = orderUnit,
    price = price.toPrice(),
    productId = productId,
    reviewScore = reviewSummary.score
)

/**
 * Convert [PriceDTO] to [Price].
 */
fun PriceDTO.toPrice(): Price = Price(
    currency = currency,
    value = value
)
