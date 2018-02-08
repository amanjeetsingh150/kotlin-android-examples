package com.developers.sqlbrite.db

import com.developers.sqlbrite.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Amanjeet Singh on 8/2/18.
 */
@Singleton
@Component(modules = arrayOf(DBModule::class))
interface AppComponent {

    fun inject(mainActivity: MainActivity)

}