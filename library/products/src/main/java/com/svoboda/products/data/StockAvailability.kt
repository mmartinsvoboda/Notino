package com.svoboda.products.data


import com.google.gson.annotations.SerializedName

data class StockAvailability(
    @SerializedName("code")
    val code: String,
    @SerializedName("count")
    val count: Int?
)
