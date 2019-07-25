package com.mudassirkhan.realitygametask.di

import com.mudassirkhan.realitygametask.ui.detail.LunchPadDetailActivity
import com.mudassirkhan.realitygametask.ui.detail.LunchPadDetailModule
import com.mudassirkhan.realitygametask.ui.main.MainActivity
import com.mudassirkhan.realitygametask.ui.main.MainActivityModule
import com.mudassirkhan.realitygametask.ui.rocket.RocketListActivity
import com.mudassirkhan.realitygametask.ui.rocket.RocketListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(LunchPadDetailModule::class)])
    abstract fun binLunchPadDetailActivity(): LunchPadDetailActivity

    @ContributesAndroidInjector(modules = [(RocketListModule::class)])
    abstract fun bindRocketList(): RocketListActivity


}