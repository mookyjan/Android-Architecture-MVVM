package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class LandingLegs(
    @SerializedName("material")
    var material: String,
    @SerializedName("number")
    var number: Int
)