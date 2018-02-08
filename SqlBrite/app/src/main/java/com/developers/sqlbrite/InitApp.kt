package com.developers.sqlbrite

import android.app.Application
import android.content.Context
import com.developers.sqlbrite.db.AppComponent
import com.developers.sqlbrite.db.DBModule
import com.developers.sqlbrite.db.DaggerAppComponent

/**
 * Created by Amanjeet Singh on 8/2/18.
 */
class InitApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .dBModule(DBModule(this))
                .build()
    }

    companion object {
        fun getComponent(context: Context): AppComponent {
            return (context.applicationContext as InitApp).appComponent
        }
    }
}