package com.developers.paginglibrary.adapter

import androidx.recyclerview.widget.DiffUtil
import com.developers.paginglibrary.model.Result

class MoviesDiffUtilCallBack : DiffUtil.ItemCallback<Result>() {

    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.title == newItem.title && oldItem.posterPath == newItem.posterPath
    }

}