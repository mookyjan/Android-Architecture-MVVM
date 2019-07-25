package com.mudassirkhan.realitygametask.ui.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.realitygametask.api.repository.RealityGameRepository
import com.mudassirkhan.realitygametask.core.Router
import com.mudassirkhan.realitygametask.core.event.GenericSingleEvent
import com.mudassirkhan.realitygametask.core.event.StartActivityEvent
import com.mudassirkhan.realitygametask.core.event.StartActivityModel
import com.mudassirkhan.realitygametask.model.SingleLunchPadResponse
import com.mudassirkhan.realitygametask.util.SchedulerProvider
import io.reactivex.functions.Function
import io.reactivex.rxkotlin.subscribeBy


class LunchPadDetailViewModel(
    private val repository: RealityGameRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    //define the variables
    val loading = MutableLiveData<Boolean>()
    val lunchPad: MutableLiveData<SingleLunchPadResponse> = MutableLiveData()
    val error: MutableLiveData<Throwable> = MutableLiveData()
    val status = ObservableField<String>("")
    val locationName = ObservableField<String>("")
    val locationRegion = ObservableField<String>("")
    val detailDescription = ObservableField<String>("")
    val wikiPedia = ObservableField<String>("")
    val siteNameLong = ObservableField<String>("")
    val siteId = ObservableField<String>("")
    val eventLocation = ObservableField<String>("")
    val imgUrl = ObservableField<String>()
    val viewMapEvent = GenericSingleEvent()
    val startActivityEvent = StartActivityEvent()


    /**
     * function to get Response from LunchPadDetail api and add the image to response and update it
     */
    fun getLunchPadDetail(siteId: String, id: String) {
        //show loading
        loading.value = true
        callLunchPadDetailApi(siteId)
            .map(object : Function<SingleLunchPadResponse, SingleLunchPadResponse> {
                override fun apply(t: SingleLunchPadResponse): SingleLunchPadResponse {
                    return addImage(t, id)
                }
            })
            .subscribeBy(onSuccess = {
                loading.value = false
                lunchPad.value = it
                updateValues(it)
                Timber.d { "lunchPad  $it" }
            }, onError = {
                loading.value = false
                it.printStackTrace()
                parseError(it)
                Timber.e { it.message!! }
            })
    }

    /**
     * call the lunchPadDetail api from repository
     */
    private fun callLunchPadDetailApi(id: String) = repository.getSingleLunchPad(id)
        .compose(schedulerProvider.getSchedulersForSingle<SingleLunchPadResponse>())

    /**
     * update the value of the screen through databinding
     */
    private fun updateValues(lunchPad: SingleLunchPadResponse) {
        status.set(lunchPad.status)
        locationName.set(lunchPad.location.name)
        wikiPedia.set(lunchPad.wikipedia)
        locationRegion.set(lunchPad.location.region)
        detailDescription.set(lunchPad.details)
        siteNameLong.set(lunchPad.siteNameLong)
        siteId.set(lunchPad.siteId)
        imgUrl.set(lunchPad.image)
        val loc: String = "${lunchPad.location.latitude},${lunchPad.location.longitude}"
        eventLocation.set(loc)
    }

    /**
     * function to open map onclick
     */
    fun onNavigateViewMap() {
        viewMapEvent.value = true
    }

    /**
     * function to add image to every item this is only for testing that how we can update the response
     */
    fun addImage(lunchPadResponse: SingleLunchPadResponse, id: String): SingleLunchPadResponse {
        lunchPadResponse.image = id
        return lunchPadResponse

    }

    /**
     * function to get the rocket detail based on the location of the Lunched Rocket
     */
    fun onGetRocketRocketByLocation() {
        startActivityEvent.value = StartActivityModel(
            Router.Destination.ROCKET_LIST,
            hashMapOf(Pair(Router.Parameter.LOCATION, lunchPad.value!!.vehiclesLaunched))
        )
    }

    //this error handling can be improved much more but for simplicity of the code now keep it simple
    private fun parseError(it: Throwable) {
        error.postValue(it)
    }
}