package com.developers.usingroom.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developers.usingroom.R
import com.developers.usingroom.SuperHero
import kotlinx.android.synthetic.main.list_row_item.*;
import kotlinx.android.synthetic.main.list_row_item.view.*

/**
 * Created by Amanjeet Singh on 14/11/17.
 */
class MyAdapter(val context: Context, val superHeroList: List<SuperHero>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindItems(superHeroList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.list_row_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return superHeroList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(superhero: SuperHero) {
            itemView.superHeroName.text = "Name: ${superhero.SuperheroName}"
            itemView.superHeroPower.text = "Power: ${superhero.Power}"
            itemView.superHeroSeries.text ="Series: ${superhero.SuperHeroSeries}"
        }
    }
}
