package com.developers.coroutineadapters

import com.developers.coroutineadapters.model.CrewResult
import com.developers.coroutineadapters.model.MovieResult
import com.developers.coroutineadapters.model.VideoResult
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
interface ApiInterface {

    @GET("popular")
    fun getMovies(@Query("api_key") key: String,
                  @Query("page") page: Int): Deferred<MovieResult>

    @GET("{id}/videos")
    fun getVideos(@Path("id") id: Int,
                  @Query("api_key") apiKey: String): Deferred<VideoResult>

    @GET("{id}/credits")
    fun getCrew(@Path("id") id: Int,
                @Query("api_key") apiKey: String): Deferred<CrewResult>


    companion object Factory {

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .baseUrl("https://api.themoviedb.org/3/movie/")
                    .build()

            return retrofit.create(ApiInterface::class.java);

        }

    }
}