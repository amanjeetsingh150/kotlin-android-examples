package com.developers.apollographqlexample

import android.content.Context
import android.widget.Toast

/**
 * Created by Amanjeet Singh on 1/2/18.
 */
fun Context.showToast(msg: String) {
    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
}