package com.svoboda.products.data


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("Action")
    val action: Boolean,
    @SerializedName("AirTransportDisallowed")
    val airTransportDisallowed: Boolean,
    @SerializedName("DifferentPackaging")
    val differentPackaging: Boolean,
    @SerializedName("FreeDelivery")
    val freeDelivery: Boolean,
    @SerializedName("Master")
    val master: Boolean,
    @SerializedName("New")
    val new: Boolean,
    @SerializedName("PackageSize")
    val packageSize: PackageSize
)
