package com.developers.mvpsample.di.component

import com.developers.mvpsample.di.PerActivity
import com.developers.mvpsample.di.module.ActivityModule
import com.developers.mvpsample.ui.main.MainActivity
import dagger.Component

/**
 * Created by Amanjeet Singh on 7/2/18.
 */
@PerActivity
@Component(modules = arrayOf(ActivityModule::class),
        dependencies = arrayOf(AppComponent::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}