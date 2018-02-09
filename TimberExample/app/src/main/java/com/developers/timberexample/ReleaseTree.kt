package com.developers.timberexample

import android.util.Log
import timber.log.Timber

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
class ReleaseTree : Timber.Tree() {


    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR && t != null) {
            //Crashlytics reporting here
        }
    }


}