package com.developers.mvpsample.ui.main

/**
 * Created by Amanjeet Singh on 7/2/18.
 */
interface Presenter<V>{

    fun attachView(view:V)

    fun detachView()

}