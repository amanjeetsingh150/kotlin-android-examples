package com.developers.mvpsample.data

import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Amanjeet Singh on 7/2/18.
 */
@Singleton
class DataManager @Inject constructor(private var apiInterface: ApiInterface) {

    fun searchMovieQuery(key: String, query: String): Observable<MovieResult> {
        return apiInterface.searchMovies(key, query)
    }

}