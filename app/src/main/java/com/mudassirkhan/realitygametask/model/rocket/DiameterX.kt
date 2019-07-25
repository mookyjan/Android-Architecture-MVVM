package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class DiameterX(
    @SerializedName("feet")
    var feet: Int,
    @SerializedName("meters")
    var meters: Int
)