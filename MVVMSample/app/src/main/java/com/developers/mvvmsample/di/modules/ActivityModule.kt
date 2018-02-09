package com.developers.mvvmsample.di.modules

import com.developers.mvvmsample.ui.MainViewModel
import com.developers.mvvmsample.utils.DataManager
import com.developers.mvvmsample.utils.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Inject

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
@Module
class ActivityModule (val schedulerProvider: SchedulerProvider){

    @Provides
    fun provideMainViewModel(dataManager: DataManager)
            : MainViewModel {
        return MainViewModel(dataManager, schedulerProvider)
    }
}