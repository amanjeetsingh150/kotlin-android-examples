package com.developers.mvvmsample.adapter

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.developers.mvvmsample.R
import java.util.logging.Logger
import com.developers.mvvmsample.model.Result
import android.content.Context
import android.view.View
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_row_movie.view.*

/**
 * Created by Amanjeet Singh on 10/2/18.
 */
class MovieAdapter(private val movieList: List<Result>, private val context: Context) :
        RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {


    companion object {
        val log = Logger.getLogger(MovieAdapter::class.java.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.list_row_movie,
                parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val title = movieList[position].title
        val imagePath = movieList[position].backdropPath
        log.info(title)
        val url = Uri.parse("http://image.tmdb.org/t/p/w185")
                .buildUpon().appendEncodedPath(imagePath).build()
        holder.bindItems(url = url.toString(), title = title)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(url: String, title: String) {
            Picasso.with(itemView.context).load(url).into(itemView.imageview_movie_element,
                    object : Callback {

                        override fun onSuccess() {
                            itemView.movie_progress.visibility = View.GONE
                        }

                        override fun onError() {

                        }

                    })
            itemView.movie_title_textview.text = title
        }
    }

}