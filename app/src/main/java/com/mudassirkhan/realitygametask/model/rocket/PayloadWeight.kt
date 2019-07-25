package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class PayloadWeight(
    @SerializedName("id")
    var id: String,
    @SerializedName("kg")
    var kg: Int,
    @SerializedName("lb")
    var lb: Int,
    @SerializedName("name")
    var name: String
)