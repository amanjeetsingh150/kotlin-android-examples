package com.developers.coroutineadapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_row.view.*

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
class MovieAdapter(private val movieNameList: MutableList<String>,
                   private val castList: MutableList<String>)
    : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent,
                false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieNameList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(movieNameList[position], castList[position])
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(movieName: String, crewResultList: String) {
            itemView.movie_title_text.text = movieName
            itemView.crew_result_text_view.text = crewResultList
        }
    }
}