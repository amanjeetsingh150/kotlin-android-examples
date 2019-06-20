package com.developers.kotlincoroutines

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val parentJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + parentJob)

    companion object {
        private var TAG = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_search.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            coroutineScope.launch(Dispatchers.Main) {
                sendMail()
            }
        }

    }

    private suspend fun sendMail() {
        val mailId = coroutineScope.async(Dispatchers.IO) { getMailFromDB() }
        val mailMsg = coroutineScope.async(Dispatchers.IO) { getMessageFromDB() }
        mail_id_text_view.text = String.format(getString(R.string.email_id_string),
                mailId.await())
        msg_text_view.text = String.format(getString(R.string.email_msg),
                mailMsg.await())
        val msg = coroutineScope.async(Dispatchers.IO) {
            sendMsgFromApi(mailId.await(), mailMsg.await())
        }
        Log.d(TAG, "Done Sending msg " + msg.await())
        sent_success_text_view.text = String.format(getString(R.string.msg_success)
                , msg.await())
        progress_bar.visibility = View.GONE
    }

    private suspend fun sendMsgFromApi(mailId: String, msg: String): String {
        //send from API
        delay(2000)
        return "$msg to $mailId"
    }

    private suspend fun getMailFromDB(): String {
        delay(3000)
        //query from DB will be here
        Log.d(TAG, "Got Mail ID from DB")
        return "amanjeetsingh150@gmail.com"
    }

    private suspend fun getMessageFromDB(): String {
        delay(3000)
        //query from DB will be here
        Log.d(TAG, "Got Msg from DB")
        return "This is an example for coroutines"
    }


}
