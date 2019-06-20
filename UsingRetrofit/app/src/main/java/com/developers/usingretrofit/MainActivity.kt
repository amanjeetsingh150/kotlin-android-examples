package com.developers.usingretrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.developers.usingretrofit.adapter.MovieAdapter
import com.developers.usingretrofit.model.MovieResult
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiCall = ApiInterface.create()
        apiCall.getMovies(BuildConfig.TV_KEY, 1).enqueue(object : Callback<MovieResult> {
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

    private fun showError(message: String?) {
        toast(message.toString())
    }

    fun Context.toast(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }
}
