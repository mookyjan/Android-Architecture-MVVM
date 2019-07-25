package com.mudassirkhan.realitygametask.api

import com.mudassirkhan.realitygametask.model.AllLunchPadResponse
import com.mudassirkhan.realitygametask.model.SingleLunchPadResponse
import com.mudassirkhan.realitygametask.model.rocket.RocketResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RealityGameService {

    @GET("launchpads")
    fun getAllLunchPads(): Single<List<AllLunchPadResponse>>

    @GET("launchpads/{site_id}")
    fun getSingleLunchPad(@Path("site_id") siteId: String): Single<SingleLunchPadResponse>

    @GET("rockets")
    fun getRocketList(): Single<List<RocketResponse>>
}