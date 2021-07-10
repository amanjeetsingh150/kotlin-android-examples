package com.developers.usingretrofit.utils

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T
) =
    lazyUI {
        bindingInflater(layoutInflater)
    }

inline fun <T : ViewBinding> RecyclerView.ViewHolder.viewBinding(
    crossinline bindingBinder: (View) -> T
) =
    lazyUI {
        bindingBinder(itemView)
    }
