package com.developers.mvvmsample.di.modules

import android.app.Application
import android.content.Context
import com.developers.mvvmsample.di.ApplicationContext
import com.developers.mvvmsample.utils.ApiInterface
import com.developers.mvvmsample.utils.DataManager
import com.developers.mvvmsample.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
@Module
class AppModule(private val app: Application) {

    @Provides
    fun providesApp(): Application {
        return app
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return app
    }

    @Provides
    fun providesSchedulers(): SchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    fun providesDataManager(apiInterface: ApiInterface): DataManager {
        return DataManager(apiInterface)
    }
}