package com.developers.broadcastreceiver


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_broadcast.setOnClickListener{
            val intent = Intent("github.amanjeetsingh150.MY_ACTION")
            intent.putExtra("data", "Broadcast Message!!")
            sendBroadcast(intent)
        }
    }


}
