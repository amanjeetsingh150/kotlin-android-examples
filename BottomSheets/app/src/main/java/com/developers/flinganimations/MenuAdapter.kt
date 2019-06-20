package com.developers.flinganimations

import android.app.Activity
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_recycler_view.view.*

/**
 * Created by Amanjeet Singh on 12/1/18.
 */
class MenuAdapter(private val context: Context, private val listImages: List<Int>) :
        RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {


    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bindItems(listImages[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_recycler_view, parent,
                false)
        return MenuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 4
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(image: Int) {
            itemView.image_card_view.setImageResource(image)
        }
    }
}