package com.developers.mvpsample.di.module

import android.app.Application
import android.content.Context
import com.developers.mvpsample.di.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by Amanjeet Singh on 6/2/18.
 */
@Module
class AppModule(val app: Application) {

    @Provides
    fun providesApp(): Application {
        return app
    }


    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return app
    }

}