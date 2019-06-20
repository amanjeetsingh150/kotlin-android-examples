package com.developers.recyclerview

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_row_item.view.*


/**
 * Created by Amanjeet Singh on 13/11/17.
 */
class MyAdapter(private val myAndroidOsList: List<String>, private val context: Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun getItemCount(): Int {
        return myAndroidOsList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItems(myAndroidOsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.list_row_item,
                parent, false)
        return MyViewHolder(v)
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(myAndroidOsListName: String) {
            itemView.android_name_text_view.text = myAndroidOsListName
        }
    }
}