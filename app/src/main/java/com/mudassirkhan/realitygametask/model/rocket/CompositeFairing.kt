package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class CompositeFairing(
    @SerializedName("diameter")
    var diameter: Diameter,
    @SerializedName("height")
    var height: Height
)