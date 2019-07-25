package com.mudassirkhan.realitygametask.ui.detail

import com.mudassirkhan.realitygametask.api.repository.RealityGameRepository
import com.mudassirkhan.realitygametask.util.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class LunchPadDetailModule {
    @Provides
    fun provideViewModel(repository: RealityGameRepository, schedulerProvider: SchedulerProvider) =
        LunchPadDetailViewModel(repository, schedulerProvider)
}