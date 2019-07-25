package com.mudassirkhan.realitygametask.ui.rocket

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.realitygametask.api.repository.RealityGameRepository
import com.mudassirkhan.realitygametask.model.rocket.RocketResponse
import com.mudassirkhan.realitygametask.util.SchedulerProvider
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class RocketListViewModel(
    private val repository: RealityGameRepository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {

    var rocketList: MutableLiveData<List<RocketResponse>> = MutableLiveData()
    val loading = MutableLiveData<Boolean>()

    fun getRocketList(rocketNameList: List<String>) {
        loading.value = true
// .map{
//                rocketName->rocketName.rocketName
//            }.filter{
//                it.equals("Falcon 1")
//            }

        var observable2 = arrayListOf<String>("Falcon 1", "Falcon Heavy")
        callRocketListApi()

        callRocketListApi()
//            .flatMap {
//                it.stream().filter {e->{
//                    var properties=HashSet<>(Arrays.asList(observable2))
//
//                }
//                }
//                it.stream().anyMatch {
//                    it.rocketName[0].equals(observable2)
//                }
//                return@flatMap
//            }
            .toObservable()
            .flatMapIterable { list ->
                list

            }.filter {

                //                observable2.iterator().equals(it.rocketName)
                rocketNameList.contains(it.rocketName)
//                observable2.forEach { name->
//                  return name.equals(it.rocketName)
//                }

//                it.rocketName.equals("Falcon 1")
            }
            .toList()
            .subscribeBy(onSuccess = {
                loading.value = false
                Timber.d { "onSuccess ${it.size}  $it" }
                rocketList.postValue(it)
            }, onError = {
                loading.value = false
                it.printStackTrace()
                Timber.d { "onError $it" }
            })
    }

    fun callRocketListApi(): Single<List<RocketResponse>> = repository.getRocketList()
        .compose(schedulerProvider.getSchedulersForSingle<List<RocketResponse>>())
}