package com.developers.paginglibrary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.developers.paginglibrary.adapter.MoviePagedListAdapter
import com.developers.paginglibrary.utils.State
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val moviesAdapter by lazy { MoviePagedListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        initializeMoviesRecyclerView()

        // Observe changes of paging list
        viewModel.getPopularMovies().observe(this, Observer {
            moviesAdapter.submitList(it)
        })
        viewModel.getState().observe(this, Observer {
            if (!viewModel.listIsEmpty()) {
                moviesAdapter.setState(it ?: State.DONE)
            }
        })
    }

    private fun initializeMoviesRecyclerView() {
        popular_movies_list.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        popular_movies_list.adapter = moviesAdapter
    }
}
