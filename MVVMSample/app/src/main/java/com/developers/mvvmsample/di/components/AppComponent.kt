package com.developers.mvvmsample.di.components

import android.content.Context
import com.developers.mvvmsample.di.ApplicationContext
import com.developers.mvvmsample.di.modules.ActivityModule
import com.developers.mvvmsample.di.modules.AppModule
import com.developers.mvvmsample.di.modules.NetModule
import com.developers.mvvmsample.utils.DataManager
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class))
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun dataManager(): DataManager


}