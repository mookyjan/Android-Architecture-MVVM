package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class Engines(
    @SerializedName("engine_loss_max")
    var engineLossMax: Any,
    @SerializedName("layout")
    var layout: Any,
    @SerializedName("number")
    var number: Int,
    @SerializedName("propellant_1")
    var propellant1: String,
    @SerializedName("propellant_2")
    var propellant2: String,
    @SerializedName("thrust_sea_level")
    var thrustSeaLevel: ThrustSeaLevel,
    @SerializedName("thrust_to_weight")
    var thrustToWeight: Any,
    @SerializedName("thrust_vacuum")
    var thrustVacuum: ThrustVacuum,
    @SerializedName("type")
    var type: String,
    @SerializedName("version")
    var version: String
)