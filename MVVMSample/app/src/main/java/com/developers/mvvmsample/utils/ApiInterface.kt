package com.developers.mvvmsample.utils

import com.developers.mvvmsample.model.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
interface ApiInterface {

    @GET("movie")
    fun searchMovies(@Query("api_key") key: String,
                     @Query("query") query: String): Observable<MovieResult>

}