package com.developers.moshiexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.developers.moshiexample.adapter.MovieAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {


    companion object {
        val log= Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val apiCall = ApiInterface.create()
        apiCall.getPopular(BuildConfig.MOVIE_KEY, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result ->
                    val linearLayoutManager = LinearLayoutManager(applicationContext)
                    linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
                    val movieAdapter = MovieAdapter(applicationContext, result.results)
                    with(movie_recycler_view) {
                        adapter = movieAdapter
                        layoutManager = linearLayoutManager
                    }
                }, { e ->
                    e.printStackTrace()
                })
    }
}
