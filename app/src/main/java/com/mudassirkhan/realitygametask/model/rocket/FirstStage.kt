package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class FirstStage(
    @SerializedName("burn_time_sec")
    var burnTimeSec: Any,
    @SerializedName("engines")
    var engines: Int,
    @SerializedName("fuel_amount_tons")
    var fuelAmountTons: Double,
    @SerializedName("reusable")
    var reusable: Boolean,
    @SerializedName("thrust_sea_level")
    var thrustSeaLevel: ThrustSeaLevelX,
    @SerializedName("thrust_vacuum")
    var thrustVacuum: ThrustVacuumX
)