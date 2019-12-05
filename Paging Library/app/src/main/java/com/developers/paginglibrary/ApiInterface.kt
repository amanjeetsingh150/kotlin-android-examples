package com.developers.paginglibrary

import com.developers.paginglibrary.model.MovieResult
import com.developers.paginglibrary.utils.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("popular")
    suspend fun getPopularMovies(@Query("api_key") key: String,
                                 @Query("page") page: Int): MovieResult

    companion object Factory {

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiInterface::class.java);
        }
    }
}