package com.svoboda.products.data


import com.google.gson.annotations.SerializedName

data class ProductDTO(
    /**
     * Short description of the product
     */
    @SerializedName("annotation")
    val `annotation`: String,

    /**
     * Additional attributes connected to the product, like whether it's new etc.
     */
    @SerializedName("attributes")
    val attributes: Attributes,

    /**
     * Information about the brand of the product
     */
    @SerializedName("brand")
    val brand: Brand,

    /**
     * Part of the url of the images product. MUST BE PREFIXED.
     */
    @SerializedName("imageUrl")
    val imageUrl: String,

    /**
     * Id of the product.
     */
    @SerializedName("masterId")
    val masterId: Int,

    /**
     * Name of the product
     */
    @SerializedName("name")
    val name: String,

    /**
     * Unit in which the item can be ordered, for example "ks", "kg", etc.
     */
    @SerializedName("orderUnit")
    val orderUnit: String,

    /**
     * Model describing the price, currency and number value.
     */
    @SerializedName("price")
    val price: PriceDTO,

    /**
     * Code of the product.
     */
    @SerializedName("productCode")
    val productCode: String,

    /**
     * Id of the product.
     */
    @SerializedName("productId")
    val productId: Int,

    /**
     * Review values entered by users.
     */
    @SerializedName("reviewSummary")
    val reviewSummary: ReviewSummary,

    /**
     * Information how many items of this product are available.
     */
    @SerializedName("stockAvailability")
    val stockAvailability: StockAvailability,

    /**
     * Url to get the detail of the product.
     */
    @SerializedName("url")
    val url: String
)
