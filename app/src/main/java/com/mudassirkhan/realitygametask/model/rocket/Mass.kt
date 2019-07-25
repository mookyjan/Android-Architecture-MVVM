package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class Mass(
    @SerializedName("kg")
    var kg: Int,
    @SerializedName("lb")
    var lb: Int
)