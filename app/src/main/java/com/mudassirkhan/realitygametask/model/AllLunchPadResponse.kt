package com.mudassirkhan.realitygametask.model

import com.google.gson.annotations.SerializedName


data class AllLunchPadResponse(
    @SerializedName("attempted_launches")
    val attemptedLaunches: Int,
    @SerializedName("details")
    val details: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("location")
    val location: Location,
    @SerializedName("site_id")
    val siteId: String,
    @SerializedName("site_name_long")
    val siteNameLong: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("successful_launches")
    val successfulLaunches: Int,
    @SerializedName("vehicles_launched")
    val vehiclesLaunched: List<String>,
    @SerializedName("wikipedia")
    val wikipedia: String,
    var image: String
)
