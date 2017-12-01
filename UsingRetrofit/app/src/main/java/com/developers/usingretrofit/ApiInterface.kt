package com.developers.usingretrofit

import com.developers.usingretrofit.model.MovieResult
import com.developers.usingretrofit.utils.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Amanjeet Singh on 29/11/17.
 */
interface ApiInterface {

    @GET("popular")
    fun getMovies(@Query("api_key") key: String,
                  @Query("page") page: Int): Call<MovieResult>

    companion object Factory {

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()

            return retrofit.create(ApiInterface::class.java);

        }

    }

}