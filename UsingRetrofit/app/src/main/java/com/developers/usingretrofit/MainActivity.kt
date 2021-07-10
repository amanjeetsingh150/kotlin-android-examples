package com.developers.usingretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.developers.usingretrofit.BuildConfig.TV_KEY
import com.developers.usingretrofit.adapter.MovieAdapter
import com.developers.usingretrofit.databinding.ActivityMainBinding
import com.developers.usingretrofit.model.MovieResult
import com.developers.usingretrofit.utils.lazyUI
import com.developers.usingretrofit.utils.showMessage
import com.developers.usingretrofit.utils.viewBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    private val apiCall by lazyUI(ApiInterface::create)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiCall.getMovies(TV_KEY, 1).enqueue(object : Callback<MovieResult> {
            override fun onFailure(call: Call<MovieResult>?, t: Throwable?) {
                showError(t?.message)
            }

            override fun onResponse(call: Call<MovieResult>?, response: Response<MovieResult>?) {
                val movieResponse = response?.body()
                val resultList = movieResponse?.results
                val layoutManager = GridLayoutManager(applicationContext,2)
                val adapter = MovieAdapter(applicationContext, resultList)
                movie_recycler_view.layoutManager = layoutManager
                movie_recycler_view.adapter = adapter
            }

        })
    }

    private fun showError(message: String?) =
        showMessage(message.toString())
}
