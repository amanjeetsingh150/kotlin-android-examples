package com.developers.paginglibrary

import androidx.lifecycle.*
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.developers.paginglibrary.model.Result
import com.developers.paginglibrary.utils.State

class MainViewModel : ViewModel() {

    private var moviesLiveData: LiveData<PagedList<Result>>
    private lateinit var dataSourceFactory: DataSource.Factory<Int, Result>
    private val moviesDataSourceLiveData = MutableLiveData<MovieDataSource>()

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setEnablePlaceholders(true)
            .build()
        moviesLiveData = initializedPagedListBuilder(config = config).build()
    }

    fun getPopularMovies() = moviesLiveData

    fun getState(): LiveData<State> {
        return Transformations.switchMap<MovieDataSource, State>(
            moviesDataSourceLiveData,
            MovieDataSource::state
        )
    }


    fun listIsEmpty(): Boolean {
        return moviesLiveData.value?.isEmpty() ?: true
    }

    private fun initializedPagedListBuilder(config: PagedList.Config): LivePagedListBuilder<Int, Result> {

        dataSourceFactory = object : DataSource.Factory<Int, Result>() {
            override fun create(): DataSource<Int, Result> {
                val movieDataSource = MovieDataSource(viewModelScope)
                moviesDataSourceLiveData.postValue(movieDataSource)
                return movieDataSource
            }
        }
        return LivePagedListBuilder<Int, Result>(dataSourceFactory, config)
    }
}