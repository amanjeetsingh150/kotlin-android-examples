package com.developers.paginglibrary

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.developers.paginglibrary.model.Result
import com.developers.paginglibrary.utils.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MovieDataSource(private val coroutineScope: CoroutineScope) :
    PageKeyedDataSource<Int, Result>() {

    private val apiInterface = ApiInterface.create()
    // Exception Handler
    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        println("Exception Handleed {$e}")
    }

    var state: MutableLiveData<State> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Result>
    ) {
        coroutineScope.launch(coroutineExceptionHandler) {
            state.value = State.LOADING
            val moviesResponse =
                apiInterface.getPopularMovies(key = BuildConfig.MOVIES_KEY, page = 1)
            state.value = State.DONE
            callback.onResult(moviesResponse.results, null, 2)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        coroutineScope.launch(coroutineExceptionHandler) {
            state.value = State.LOADING
            val moviesResponse = apiInterface.getPopularMovies(
                key = BuildConfig.MOVIES_KEY,
                page = params.key
            )
            val nextKey = params.key + 1
            state.value = State.DONE
            callback.onResult(moviesResponse.results, nextKey)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Result>) {
        // No-op
    }

}