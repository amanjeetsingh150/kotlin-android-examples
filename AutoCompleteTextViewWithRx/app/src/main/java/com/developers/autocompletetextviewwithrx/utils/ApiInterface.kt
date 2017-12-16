package com.developers.autocompletetextviewwithrx.utils

import com.developers.autocompletetextviewwithrx.model.AutoCompleteResult
import com.developers.autocompletetextviewwithrx.model.DetailsResult
import com.google.gson.Gson
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Amanjeet Singh on 2/12/17.
 */
interface ApiInterface {

    @GET("autocomplete/json")
    fun getResults(@Query("input") input: String,
                   @Query("types") types: String,
                   @Query("key") key: String): Observable<AutoCompleteResult>

    @GET("details/json")
    fun getDetails(@Query("placeid") placeId: String,
                   @Query("key") key: String): Observable<DetailsResult>

    companion object {

        fun create(): ApiInterface {
            val retroFit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .build()
            return retroFit.create(ApiInterface::class.java)
        }

    }
}