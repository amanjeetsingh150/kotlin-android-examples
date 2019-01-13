package com.developers.jacksonkotlinmodule

import com.developers.jacksonkotlinmodule.model.MovieResult
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
interface ApiInterface {
    @GET("popular")
    fun getMovies(@Query("api_key") key: String,
                  @Query("page") page: Int): Call<MovieResult>

    companion object Factory {

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(JacksonConverterFactory.create(
                            jacksonObjectMapper()
                    ))
                    .baseUrl("https://api.themoviedb.org/3/movie/")
                    .build()

            return retrofit.create(ApiInterface::class.java);

        }

    }
}