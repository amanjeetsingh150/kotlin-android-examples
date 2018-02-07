package com.developers.mvpsample

import android.app.Application
import android.content.Context
import com.developers.mvpsample.di.component.AppComponent
import com.developers.mvpsample.di.component.DaggerAppComponent
import com.developers.mvpsample.di.module.AppModule
import com.developers.mvpsample.di.module.NetModule

/**
 * Created by Amanjeet Singh on 6/2/18.
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