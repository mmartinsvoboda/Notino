package com.svoboda.products.data


import com.google.gson.annotations.SerializedName

data class PackageSize(
    @SerializedName("depth")
    val depth: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("width")
    val width: Int
)
