package com.developers.kodein

import com.developers.kodein.model.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Amanjeet Singh on 24/1/18.
 */
interface ApiInterface{

    @GET("popular")
    fun getMovies(@Query("api_key") key: String,
                  @Query("page") page: Int): Observable<MovieResult>
}