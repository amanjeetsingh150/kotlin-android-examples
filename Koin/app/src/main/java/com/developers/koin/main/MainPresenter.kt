package com.developers.koin.main

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
class MainPresenter() {

    private var mainView: MainView? = null

    fun attachView(mainView: MainView?) {
        this.mainView = mainView
    }

    fun detachView() {
        mainView = null
    }

    fun showMessage() {
        mainView?.showMessage("Hello this is Injection from Koin")
    }


}