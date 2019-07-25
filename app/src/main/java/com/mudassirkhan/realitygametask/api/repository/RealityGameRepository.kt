package com.mudassirkhan.realitygametask.api.repository

import com.mudassirkhan.realitygametask.api.RealityGameService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealityGameRepository @Inject constructor(private val api: RealityGameService) {

    fun getAllLunchPads() = api.getAllLunchPads()

    fun getSingleLunchPad(siteId: String) = api.getSingleLunchPad(siteId)

    fun getRocketList() = api.getRocketList()

}