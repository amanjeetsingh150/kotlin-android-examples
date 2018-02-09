package com.developers.mvvmsample.di.components

import com.developers.mvvmsample.di.PerActivity
import com.developers.mvvmsample.di.modules.ActivityModule
import com.developers.mvvmsample.di.modules.AppModule
import com.developers.mvvmsample.di.modules.NetModule
import com.developers.mvvmsample.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
@PerActivity
@Component(modules = arrayOf(NetModule::class, ActivityModule::class),
        dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
}