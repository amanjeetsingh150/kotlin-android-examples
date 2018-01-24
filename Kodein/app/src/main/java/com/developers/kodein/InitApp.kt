package com.developers.kodein

import android.app.Application
import com.github.salomonbrys.kodein.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Amanjeet Singh on 24/1/18.
 */
class InitApp : Application(), KodeinAware {

    override val kodein: Kodein = Kodein {
        bind<Retrofit>() with singleton {
            Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl("https://api.themoviedb.org/3/movie/")
                    .build()
        }
    }

    override fun onCreate() {
        super.onCreate()

    }

}