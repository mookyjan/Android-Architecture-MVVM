package com.mudassirkhan.realitygametask

import android.app.Activity
import android.app.Application
import com.github.ajalt.timberkt.Timber
import com.mudassirkhan.realitygametask.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RealityGameApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        initLogger()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingAndroidInjector

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(timber.log.Timber.DebugTree())
        }
    }

}