package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class SecondStage(
    @SerializedName("burn_time_sec")
    var burnTimeSec: Any,
    @SerializedName("engines")
    var engines: Int,
    @SerializedName("fuel_amount_tons")
    var fuelAmountTons: Double,
    @SerializedName("payloads")
    var payloads: Payloads,
    @SerializedName("reusable")
    var reusable: Boolean,
    @SerializedName("thrust")
    var thrust: Thrust
)