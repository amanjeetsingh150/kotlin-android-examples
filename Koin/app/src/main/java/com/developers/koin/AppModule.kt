package com.developers.koin

import android.app.Application
import org.koin.dsl.module.applicationContext

/**
 * Created by Amanjeet Singh on 10/2/18.
 */

val app = applicationContext {
    provide { Application() }
}
