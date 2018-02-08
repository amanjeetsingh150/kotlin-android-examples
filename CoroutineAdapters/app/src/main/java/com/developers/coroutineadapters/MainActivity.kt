package com.developers.coroutineadapters

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.developers.coroutineadapters.model.Cast
import com.developers.coroutineadapters.model.MovieResult
import com.developers.coroutineadapters.model.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI

import kotlinx.coroutines.experimental.launch
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface
    private var nameList = mutableListOf<String>()
    private var castList = mutableListOf<String>()

    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = ApiInterface.create()
        launch(CommonPool) {
            getMovieCrew(getMovies().await().results)
        }

    }

    private fun getMovies(): Deferred<MovieResult> {
        return apiInterface.getMovies(BuildConfig.MOVIE_KEY, 1)
    }

    private suspend fun getMovieCrew(movieList: List<Result>) {
        for (result in movieList) {
            nameList.add(result.title)
            castList.add(apiInterface.getCrew(result.id,
                    BuildConfig.MOVIE_KEY).await().cast[0].name)
            log.info(apiInterface.getCrew(result.id,
                    BuildConfig.MOVIE_KEY).await().cast[0].name + " of " + result.title)
        }
        log.info(" " + castList.size)
        launch(UI) {
            log.info("Sizes " + nameList.size + " " + castList.size)
            val linearLayoutManager = LinearLayoutManager(applicationContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            val movieAdapter = MovieAdapter(nameList, castList)
            with(movie_crew_recycler_view) {
                layoutManager = linearLayoutManager
                adapter = movieAdapter
            }
            progress_bar.visibility = View.GONE
        }
    }


}
