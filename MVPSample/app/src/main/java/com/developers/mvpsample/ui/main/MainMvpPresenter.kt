package com.developers.mvpsample.ui.main

/**
 * Created by Amanjeet Singh on 6/2/18.
 */
interface MainMvpPresenter<V : MainView> : Presenter<V> {

    fun searchMovieQuery(query: String, key: String)

}