package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class Payloads(
    @SerializedName("composite_fairing")
    var compositeFairing: CompositeFairing,
    @SerializedName("option_1")
    var option1: String,
    @SerializedName("option_2")
    var option2: String
)