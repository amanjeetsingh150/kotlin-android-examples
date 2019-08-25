package com.developers.listadapter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.developers.listadapter.model.Language

class LanguageDiffUtilCallback : DiffUtil.ItemCallback<Language>() {

    override fun areItemsTheSame(oldItem: Language, newItem: Language): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Language, newItem: Language): Boolean {
        return oldItem == newItem
    }

}