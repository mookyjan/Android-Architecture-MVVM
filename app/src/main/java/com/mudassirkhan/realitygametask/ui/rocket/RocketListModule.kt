package com.mudassirkhan.realitygametask.ui.rocket

import com.mudassirkhan.realitygametask.api.repository.RealityGameRepository
import com.mudassirkhan.realitygametask.util.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class RocketListModule {

    @Provides
    fun provideViewModel(repository: RealityGameRepository, schedulerProvider: SchedulerProvider) =
        RocketListViewModel(repository, schedulerProvider)
}