package com.mudassirkhan.realitygametask.model.rocket


import com.google.gson.annotations.SerializedName

data class RocketResponse(
    @SerializedName("active")
    var active: String,
    @SerializedName("boosters")
    var boosters: Int,
    @SerializedName("company")
    var company: String,
    @SerializedName("cost_per_launch")
    var costPerLaunch: Int,
    @SerializedName("country")
    var country: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("diameter")
    var diameter: Diameter,
    @SerializedName("engines")
    var engines: Engines,
    @SerializedName("first_flight")
    var firstFlight: String,
    @SerializedName("first_stage")
    var firstStage: FirstStage,
    @SerializedName("flickr_images")
    var flickrImages: List<String>,
    @SerializedName("height")
    var height: Height,
    @SerializedName("id")
    var id: Int,
    @SerializedName("landing_legs")
    var landingLegs: LandingLegs,
    @SerializedName("mass")
    var mass: Mass,
    @SerializedName("payload_weights")
    var payloadWeights: List<PayloadWeight>,
    @SerializedName("rocket_id")
    var rocketId: String,
    @SerializedName("rocket_name")
    var rocketName: String,
    @SerializedName("rocket_type")
    var rocketType: String,
    @SerializedName("second_stage")
    var secondStage: SecondStage,
    @SerializedName("stages")
    var stages: Int,
    @SerializedName("success_rate_pct")
    var successRatePct: Int,
    @SerializedName("wikipedia")
    var wikipedia: String
)