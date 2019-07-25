package com.mudassirkhan.realitygametask.util

import io.reactivex.Scheduler
import io.reactivex.Single

class SchedulerProvider(private val backgroundScheduler: Scheduler, private val foregroundScheduler: Scheduler) {

    fun <T> getSchedulersForSingle(): (Single<T>) -> Single<T> {

        return { single: Single<T> ->
            single.subscribeOn(backgroundScheduler)
                .observeOn(foregroundScheduler)
        }
    }
}