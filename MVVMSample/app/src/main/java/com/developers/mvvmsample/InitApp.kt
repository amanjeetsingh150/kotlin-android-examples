package com.developers.mvvmsample

import android.app.Application
import android.content.Context
import com.developers.mvvmsample.di.components.AppComponent
import com.developers.mvvmsample.di.components.DaggerAppComponent
import com.developers.mvvmsample.di.modules.AppModule
import com.developers.mvvmsample.di.modules.NetModule

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
class InitApp : Application() {

    companion object {
        fun get(context: Context): InitApp {
            return context.applicationContext as InitApp
        }
    }

    fun component(): AppComponent {
        val appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
        return appComponent
    }
}