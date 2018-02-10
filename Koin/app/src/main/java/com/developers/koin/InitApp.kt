package com.developers.koin

import android.app.Application
import com.developers.koin.main.mainModule
import org.koin.android.ext.android.startKoin

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
class InitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(app, mainModule))
    }
}