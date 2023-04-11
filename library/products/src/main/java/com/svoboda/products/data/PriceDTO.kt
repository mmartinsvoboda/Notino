package com.svoboda.products.data


import com.google.gson.annotations.SerializedName

data class PriceDTO(
    /**
     * Currency of the price
     */
    @SerializedName("currency")
    val currency: String,
    /**
     * Price as a number, can be combined with the currency.
     */
    @SerializedName("value")
    val value: Int
)
