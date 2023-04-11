package com.svoboda.products.data


import com.google.gson.annotations.SerializedName

data class ReviewSummary(
    /**
     * Exact average rating of the product
     */
    @SerializedName("averageRating")
    val averageRating: Double,

    /**
     * Rating of the product rounded up to the whole number.
     */
    @SerializedName("score")
    val score: Int
)
