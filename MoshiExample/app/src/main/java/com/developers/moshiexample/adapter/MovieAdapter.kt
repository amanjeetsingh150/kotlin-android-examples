package com.developers.moshiexample.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developers.moshiexample.R
import com.developers.moshiexample.model.Result
import kotlinx.android.synthetic.main.list_row.view.*
import java.util.logging.Logger

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
class MovieAdapter(private val context: Context, private val resultList: List<Result>?)
    : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(resultList?.get(position))
    }

    override fun getItemCount(): Int {
        return resultList?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_row, parent
                , false)
        return MyViewHolder(view)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(result: Result?) {
            itemView.movie_title.text = result?.title
            itemView.overview_movie.text = result?.overview
            itemView.progress_bar.visibility = View.GONE
        }
    }
}