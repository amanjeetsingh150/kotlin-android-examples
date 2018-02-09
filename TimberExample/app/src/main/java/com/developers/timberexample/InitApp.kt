package com.developers.timberexample

import android.app.Application
import timber.log.Timber

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
class InitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            //Debug Mode
            Timber.plant(Timber.DebugTree())
        } else {
            //Release Mode
            //Init crashlytics here
            Timber.plant(ReleaseTree())
        }
    }
}