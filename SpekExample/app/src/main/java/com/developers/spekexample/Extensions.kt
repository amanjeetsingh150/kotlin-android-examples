package com.developers.spekexample

import android.content.Context
import android.widget.Toast

/**
 * Created by Amanjeet Singh on 26/1/18.
 */
fun Context.toast(msg: CharSequence, duration: Int) {
    Toast.makeText(applicationContext, msg, duration).show()
}