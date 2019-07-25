package com.mudassirkhan.realitygametask.ui.main

import com.mudassirkhan.realitygametask.api.repository.RealityGameRepository
import com.mudassirkhan.realitygametask.util.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    fun provideViewModel(repository: RealityGameRepository, schedulerProvider: SchedulerProvider) =
        MainViewModel(repository, schedulerProvider)
}