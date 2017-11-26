package com.developers.usingdagger2.di.module

import dagger.Module
import android.app.Activity
import android.content.Context
import com.developers.usingdagger2.main.MainPresenter
import dagger.Provides
import com.developers.usingdagger2.di.ActivityContext
import com.developers.usingdagger2.di.PerActivity


/**
 * Created by Amanjeet Singh on 26/11/17.
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
    fun provideMainPresenter(): MainPresenter {
        return MainPresenter()
    }

}