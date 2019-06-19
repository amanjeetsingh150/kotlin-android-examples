package com.developers.coroutineadapters

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.developers.coroutineadapters.model.MovieResult
import com.developers.coroutineadapters.model.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var apiInterface: ApiInterface
    private var nameList = mutableListOf<String>()
    private var castList = mutableListOf<String>()

    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = ApiInterface.create()
        GlobalScope.launch(Dispatchers.IO) {
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
        GlobalScope.launch(Dispatchers.Main) {
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
