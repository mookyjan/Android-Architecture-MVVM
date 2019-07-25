package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class Diameter(
    @SerializedName("feet")
    var feet: Any,
    @SerializedName("meters")
    var meters: Any
)