package com.developers.koin.main

import org.koin.dsl.module.applicationContext

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
val mainModule = applicationContext {
    provide { MainPresenter() }
}
