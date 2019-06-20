package com.developers.kodein

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.developers.kodein.adapter.MovieAdapter
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.github.salomonbrys.kodein.instance
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : KodeinAppCompatActivity() {

    private val retrofit: Retrofit by injector.instance()
    private lateinit var apiInterface: ApiInterface
    private var apiDisposable: Disposable? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeInjector()
        apiInterface = retrofit.create(ApiInterface::class.java)
        apiDisposable = apiInterface.getMovies(BuildConfig.MOVIE_KEY, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ movieList ->
                    val adapter = MovieAdapter(applicationContext, movieList.results)
                    movie_recycler_view.adapter = adapter
                    movie_recycler_view.layoutManager = GridLayoutManager(applicationContext
                            , 2)
                }, { e -> e.printStackTrace() })
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyInjector()
    }

    override fun onStop() {
        super.onStop()
        apiDisposable?.dispose()
    }
}


