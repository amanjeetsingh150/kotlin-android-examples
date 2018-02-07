package com.developers.mvpsample.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.developers.mvpsample.BuildConfig
import com.developers.mvpsample.InitApp
import com.developers.mvpsample.R
import com.developers.mvpsample.data.Result
import com.developers.mvpsample.di.component.DaggerActivityComponent
import com.developers.mvpsample.di.module.ActivityModule
import com.developers.mvpsample.ui.adapter.MovieAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainMvpPresenter<MainView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val activityComponent = DaggerActivityComponent.builder()
                .appComponent(InitApp.get(this).component())
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
        mainPresenter.attachView(this)
        setListeners()
    }

    private fun setListeners() {
        search_button.setOnClickListener({
            mainPresenter.searchMovieQuery(query_edit_text.text.toString()
                    , BuildConfig.MOVIE_KEY)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun showData(movieResult: List<Result>) {
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        val movieAdapter = MovieAdapter(movieResult, applicationContext)
        with(movie_recycler_view) {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }
    }

    override fun showError(error: String) {
        Snackbar.make(layout_main, error, Snackbar.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        progress_bar.visibility = View.GONE
    }

}
