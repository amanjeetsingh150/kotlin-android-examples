package com.developers.paginglibrary.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.developers.paginglibrary.R
import com.developers.paginglibrary.model.Result
import com.developers.paginglibrary.utils.State
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_item.view.*

class MoviePagedListAdapter :
    PagedListAdapter<Result, RecyclerView.ViewHolder>(MoviesDiffUtilCallBack()) {

    private var state = State.LOADING

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == DATA_VIEW_TYPE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movies_item, parent, false)
            MoviesViewHolder(view)
        } else {
            val loadingView = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_item, parent, false)
            LoadingViewHolder(loadingView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_VIEW_TYPE) {
            val moviesViewHolder = holder as MoviesViewHolder
            getItem(position)?.let { moviesViewHolder.bind(it) }
        } else {
            (holder as LoadingViewHolder).bind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == State.LOADING || state == State.ERROR)
    }

    fun setState(state: State) {
        this.state = state
        notifyItemChanged(super.getItemCount())
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Result) {
            with(movie) {
                itemView.movie_title.text = title
                val url = "https://image.tmdb.org/t/p/w780/${backdropPath}"
                Picasso.with(itemView.context).load(url).into(itemView.image_view_movie)
            }
        }
    }

    companion object {
        private const val DATA_VIEW_TYPE = 1
        private const val FOOTER_VIEW_TYPE = 2
    }
}