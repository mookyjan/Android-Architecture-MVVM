package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class ThrustSeaLevelX(
    @SerializedName("kN")
    var kN: Int,
    @SerializedName("lbf")
    var lbf: Int
)