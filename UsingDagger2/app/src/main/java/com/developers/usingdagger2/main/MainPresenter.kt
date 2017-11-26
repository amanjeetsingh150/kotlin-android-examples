package com.developers.usingdagger2.main

/**
 * Created by Amanjeet Singh on 26/11/17.
 */
class MainPresenter() {

    lateinit var mainView: MainView

    fun setView(mainView: MainView) {
        this.mainView = mainView
    }

    fun showData(){
        mainView.showLoading()
        mainView.showMessage("Hey Dagger2 Worked");
        mainView.hideLoading()
    }

}