package com.developers.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class MyReceiver : BroadcastReceiver() {

    private val action: String = "github.amanjeetsingh150.MY_ACTION"

    override fun onReceive(context: Context, intent: Intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        val intentName = intent.action

        if (intentName == action) {
            val s = intent.extras?.getString("data")
            context.toast("Broadcast received!! {$s}")
        }

    }

    private fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }


}
