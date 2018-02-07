package com.developers.mvpsample.di.module

import android.app.Activity
import android.content.Context
import com.developers.mvpsample.di.ActivityContext
import dagger.Module
import dagger.Provides
import com.developers.mvpsample.ui.main.MainView
import com.developers.mvpsample.ui.main.MainPresenter
import com.developers.mvpsample.ui.main.MainMvpPresenter
import com.developers.mvpsample.di.PerActivity


/**
 * Created by Amanjeet Singh on 6/2/18.
 */
@Module
class ActivityModule(val activity: Activity) {

    @Provides
    fun providesActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityContext
    fun providesContext(): Context {
        return activity
    }

    @PerActivity
    @Provides
    fun providesMainPresenter(mainMvpPresenter: MainPresenter<MainView>):
            MainMvpPresenter<MainView> {
        return mainMvpPresenter
    }

}