package com.developers.listadapter.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.developers.listadapter.model.Language
import kotlinx.android.synthetic.main.language_list.view.*

class LanguagesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindItem(item: Language) {
        itemView.languageName.text = item.name
        itemView.languageDescription.text = item.description
    }
}