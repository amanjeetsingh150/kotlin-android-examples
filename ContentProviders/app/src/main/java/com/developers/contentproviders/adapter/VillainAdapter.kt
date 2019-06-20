package com.developers.contentproviders.adapter

import android.content.Context
import android.database.Cursor
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developers.contentproviders.MainActivity
import com.developers.contentproviders.R
import com.developers.contentproviders.data.Villains
import kotlinx.android.synthetic.main.item_row.view.*
import java.util.logging.Logger

/**
 * Created by Amanjeet Singh on 25/11/17.
 */
class VillainAdapter(val context: Context) : RecyclerView.Adapter<VillainAdapter.MyViewHolder>() {

    lateinit var mCursor: Cursor

    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun getItemCount(): Int {
        if(mCursor.count>0){
            return mCursor.count
        }
        else{
            return 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (mCursor.moveToPosition(position)) {
            holder?.bindItems(mCursor)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(cursor: Cursor) {
            itemView.name_text_view.text =cursor.getString(cursor.getColumnIndex(Villains.VILLAIN_NAME))
            itemView.id_text_view.text = cursor.getString(cursor.getColumnIndex(Villains.COLUMN_ID)).toString()
            itemView.series_text_view.text = cursor.getString(cursor.getColumnIndex(Villains.VILLAIN_SERIES))
        }
    }

    fun setVillains(cursor: Cursor) {
        mCursor = cursor
        notifyDataSetChanged()
    }
}