package com.developers.jacksonkotlinmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.developers.jacksonkotlinmodule.adapter.MovieAdapter
import com.developers.jacksonkotlinmodule.model.MovieResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    companion object {
        val Log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiCall = ApiInterface.create()
        apiCall.getMovies(BuildConfig.MOVIE_KEY, 1)
                .enqueue(object : Callback<MovieResult> {

                    override fun onResponse(call: Call<MovieResult>?, response: Response<MovieResult>?) {
                        Log.info(response?.body()?.results!![0].posterPath + " ")
                        val movieResponse = response.body()
                        val resultList = movieResponse?.results
                        val layoutManager = GridLayoutManager(applicationContext, 2)
                        val adapter = MovieAdapter(applicationContext, resultList)
                        movie_recycler_view.layoutManager = layoutManager
                        movie_recycler_view.adapter = adapter
                    }

                    override fun onFailure(call: Call<MovieResult>?, t: Throwable?) {
                        t?.printStackTrace()
                    }

                })
    }
}
