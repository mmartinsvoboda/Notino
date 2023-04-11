package com.svoboda.products.data


import com.google.gson.annotations.SerializedName

data class Brand(
    /**
     * Id of the brand
     */
    @SerializedName("id")
    val id: String,
    /**
     * Name of the brand
     */
    @SerializedName("name")
    val name: String
)
