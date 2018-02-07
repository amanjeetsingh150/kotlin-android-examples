package com.developers.mvpsample.ui.main

import com.developers.mvpsample.data.DataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.logging.Logger
import javax.inject.Inject

/**
 * Created by Amanjeet Singh on 7/2/18.
 */
class MainPresenter<V : MainView> @Inject constructor(private var dataManager: DataManager) : MainMvpPresenter<V> {

    var view: MainView? = null

    companion object {
        val log = Logger.getLogger(MainPresenter::class.java.name)
    }


    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun searchMovieQuery(query: String, key: String) {
        view?.showLoading()
        if (query.isEmpty()) {
            view?.showError("Blank field not allowed.")
            view?.hideLoading()
        } else {
            dataManager.searchMovieQuery(key, query)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ result ->
                        val movieList = result.results
                        log.info(" " + movieList[0].title)
                        view?.showData(movieList)
                        view?.hideLoading()
                    }, { e -> view?.showError(e.message.toString()) })

        }
    }

}