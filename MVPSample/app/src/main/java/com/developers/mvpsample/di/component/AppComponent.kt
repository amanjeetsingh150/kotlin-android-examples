package com.developers.mvpsample.di.component

import android.content.Context
import com.developers.mvpsample.di.ApplicationContext
import com.developers.mvpsample.di.module.AppModule
import com.developers.mvpsample.di.module.NetModule
import dagger.Component
import javax.inject.Singleton
import com.developers.mvpsample.data.DataManager



/**
 * Created by Amanjeet Singh on 6/2/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun dataManager(): DataManager

}