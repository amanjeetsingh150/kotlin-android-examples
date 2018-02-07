package com.developers.mvpsample.ui.main

import com.developers.mvpsample.data.Result

/**
 * Created by Amanjeet Singh on 6/2/18.
 */
interface MainView {

    fun showLoading()

    fun showData(movieResult: List<Result>)

    fun showError(error: String)

    fun hideLoading()
}