package com.developers.moshiexample

import com.developers.moshiexample.model.MovieResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
interface ApiInterface {

    @GET("popular")
    fun getPopular(@Query("api_key") key: String,
                   @Query("page") page: Int): Observable<MovieResult>

    companion object {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/3/movie/")
                    .addConverterFactory(MoshiConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}