package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class Height(
    @SerializedName("feet")
    var feet: Any,
    @SerializedName("meters")
    var meters: Any
)