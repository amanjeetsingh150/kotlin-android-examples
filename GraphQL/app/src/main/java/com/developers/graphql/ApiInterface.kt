package com.developers.graphql

import com.developers.graphql.model.Result
import com.google.gson.JsonElement
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * Created by Amanjeet Singh on 27/1/18.
 */
interface ApiInterface {

    @Headers("Authorization:Bearer " + BuildConfig.AUTH_TOKEN)
    @POST("graphql")
    fun getInfo(@Body jsonQuery: Query): Observable<Result>

    companion object Factory {

        fun create(): ApiInterface {

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(ApiInterface::class.java);

        }

    }
}