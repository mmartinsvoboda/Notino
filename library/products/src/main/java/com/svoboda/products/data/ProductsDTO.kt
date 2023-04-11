package com.svoboda.products.data


import com.google.gson.annotations.SerializedName

data class ProductsDTO(
    /**
     * List of products that should be displayed to the user.
     */
    @SerializedName("vpProductByIds")
    val products: List<ProductDTO>
)
