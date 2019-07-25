package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class ThrustVacuumX(
    @SerializedName("kN")
    var kN: Int,
    @SerializedName("lbf")
    var lbf: Int
)