package com.svoboda.products.domain.model


/**
 * Domain model of price
 *
 * @property currency Currency in which the value is
 * @property value Number value of the price
 */
data class Price(
    val currency: String,
    val value: Int
)
