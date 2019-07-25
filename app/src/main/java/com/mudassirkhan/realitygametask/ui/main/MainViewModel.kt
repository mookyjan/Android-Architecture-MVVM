package com.mudassirkhan.realitygametask.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.realitygametask.api.repository.RealityGameRepository
import com.mudassirkhan.realitygametask.core.event.StartActivityEvent
import com.mudassirkhan.realitygametask.model.AllLunchPadResponse
import com.mudassirkhan.realitygametask.util.SchedulerProvider
import io.reactivex.Single
import io.reactivex.functions.Function
import io.reactivex.rxkotlin.subscribeBy

class MainViewModel(
    private val repository: RealityGameRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    //init variables
    val loading = MutableLiveData<Boolean>()
    val lunchPads: MutableLiveData<List<AllLunchPadResponse>> = MutableLiveData()
    val startActivityEvent = StartActivityEvent()
    var picId: Int = 0

    /**
     * function to call the lunchPad API to get list of lunchs and
     * also update it by adding image to it
     */
    fun getLunchPads() {
        loading.value = true
        //reset the pic Id
        picId = 0
        callAllLunchPadApi()
            .flattenAsObservable {
                it
            }.map(object : Function<AllLunchPadResponse, AllLunchPadResponse> {
                override fun apply(t: AllLunchPadResponse): AllLunchPadResponse {
                    return addImage(t)
                }
            }).toList()
            .subscribeBy(onSuccess = {
                loading.value = false
                if (it.isNotEmpty()) {
                    Timber.d { "onSuceess $it" }
                    lunchPads.postValue(it)
                }
            }, onError = {
                loading.value = false
                it.printStackTrace()
                Timber.d { it.message!! }
            })

    }


    /**
     * function to add image to the LunchPad object this work can be done by many ways
     * this is only sample for checking
     */

    private fun addImage(lunchPadResponse: AllLunchPadResponse): AllLunchPadResponse {
        val imgUrl = "https://picsum.photos/id/102" + picId + "/300/300"
        lunchPadResponse.image = imgUrl
        picId++
        return lunchPadResponse

    }

    /**
     * fun to call the api from repository
     */
    private fun callAllLunchPadApi(): Single<List<AllLunchPadResponse>> = repository.getAllLunchPads()
        .compose(schedulerProvider.getSchedulersForSingle<List<AllLunchPadResponse>>())


}


