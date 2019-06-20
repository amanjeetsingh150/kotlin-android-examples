package com.developers.mvvmsample.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import com.developers.mvvmsample.InitApp
import com.developers.mvvmsample.R
import com.developers.mvvmsample.adapter.MovieAdapter
import com.developers.mvvmsample.di.components.DaggerActivityComponent
import com.developers.mvvmsample.di.modules.ActivityModule
import com.developers.mvvmsample.model.Result
import com.developers.mvvmsample.utils.SchedulerProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger
import javax.inject.Inject
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel
    private lateinit var compositeDisposable: CompositeDisposable
    private var disposable: Disposable? = null

    companion object {
        val Log = Logger.getLogger(MainActivity::class.java.name)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable = CompositeDisposable()
        val activityComponent = DaggerActivityComponent
                .builder()
                .appComponent(InitApp.get(this).component())
                .activityModule(ActivityModule(SchedulerProvider()))
                .build()
        activityComponent.inject(this)
        search_button.setOnClickListener {
            hideKeyboard()
            val query = query_edit_text.text.toString()
            if (mainViewModel.isValidQuery(query)) {
                if (mainViewModel.loading == true) {
                    progress_bar.visibility = View.VISIBLE
                }
                disposable = mainViewModel.provideMoviesList(query)
                        ?.subscribe({ result ->
                            Log.info(result[0].title)
                            showList(result)
                            mainViewModel.setIsLoading(false)
                        }, { e -> e.printStackTrace() })
            } else {
                Snackbar.make(layout_main,
                        "Not a valid Query", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (disposable != null) {
            bind()
        }
    }

    private fun bind() {
        compositeDisposable.add(disposable!!)
    }

    private fun unbind() {
        compositeDisposable.clear()
    }

    override fun onPause() {
        super.onPause()
        if (compositeDisposable.size() > 0) {
            unbind()
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun showList(result: List<Result>) {
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        linearLayoutManager.orientation = RecyclerView.VERTICAL
        val movieAdapter = MovieAdapter(result, applicationContext)
        with(movie_recycler_view) {
            layoutManager = linearLayoutManager
            adapter = movieAdapter
        }
    }

}
