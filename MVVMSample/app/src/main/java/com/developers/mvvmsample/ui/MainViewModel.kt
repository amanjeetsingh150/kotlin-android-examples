package com.developers.mvvmsample.ui

import com.developers.mvvmsample.BuildConfig
import com.developers.mvvmsample.model.Result
import com.developers.mvvmsample.utils.DataManager
import com.developers.mvvmsample.utils.ISchedulerProvider
import com.developers.mvvmsample.utils.SchedulerProvider
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject


/**
 * Created by Amanjeet Singh on 10/2/18.
 */
class MainViewModel @Inject constructor(private var dataManager: DataManager,
                                        private var schedulerProvider: SchedulerProvider) {

    var loading: Boolean? = null

    fun setIsLoading(loading: Boolean) {
        this.loading = loading
    }


    fun provideMoviesList(query: String): Observable<List<Result>>? {
        setIsLoading(true)
        return dataManager.searchMovieQuery(BuildConfig.MOVIE_KEY, query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .map { result -> result.results }
    }

    fun isValidQuery(query: String): Boolean {
        if (query.isEmpty()) {
            return false
        }
        return true
    }
}