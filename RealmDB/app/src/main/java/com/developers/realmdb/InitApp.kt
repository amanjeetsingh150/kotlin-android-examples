package com.developers.realmdb

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Created by Amanjeet Singh on 8/2/18.
 */
class InitApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .name("example.realm").build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}