package com.developers.usingretrofit.adapter

import android.content.Context
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developers.usingretrofit.R
import com.developers.usingretrofit.databinding.ListRowBinding
import com.developers.usingretrofit.model.Result
import com.developers.usingretrofit.utils.BASE_URL
import com.developers.usingretrofit.utils.viewBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

/**
 * Created by Amanjeet Singh on 30/11/17.
 */
class MovieAdapter(val context: Context, private val resultList: List<Result>?) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) =
        holder.bindItems(resultList?.get(position))

    override fun getItemCount(): Int =
        resultList?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_row, parent, false))


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding by viewBinding(ListRowBinding::bind)

        fun bindItems(result: Result?) {
            result?.let {
                binding.apply {
                    movieTitle.text = it.title
                    binding.progressBar.visibility = View.VISIBLE

                    val posterUri = Uri.parse(BASE_URL).buildUpon()
                        .appendEncodedPath(it.posterPath)
                        .build()

                    Picasso.with(itemView.context).load(posterUri.toString())
                        .into(imageViewMovie, object : Callback {

                            override fun onError() {
                                //Show Error here
                            }

                            override fun onSuccess() {
                                progressBar.visibility = View.GONE
                            }
                        })
                }
            }
        }
    }
}