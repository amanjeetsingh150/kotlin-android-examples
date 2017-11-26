package com.developers.usingdagger2.main

/**
 * Created by Amanjeet Singh on 26/11/17.
 */
interface MainView {

    fun showLoading()

    fun showMessage(message:String)

    fun hideLoading()
}