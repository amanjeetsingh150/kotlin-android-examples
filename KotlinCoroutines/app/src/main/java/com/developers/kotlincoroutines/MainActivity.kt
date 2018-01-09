package com.developers.kotlincoroutines

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.coroutines.experimental.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    var id = 0

    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button_search.setOnClickListener({
            progress_bar.visibility = View.VISIBLE
            launch(CommonPool) {
                sendMail()
            }
        })

    }

    private suspend fun sendMail() {
        val mailId = async(CommonPool) {
            getMailFromDB()
        }
        val mailMsg = async(CommonPool) {
            getMessageFromDB()
        }
        val msg = async(CommonPool) {
            async(UI) {
                mail_id_text_view.text = String.format(getString(R.string.email_id_string),
                        mailId.await())
                msg_text_view.text = String.format(getString(R.string.email_msg),
                        mailMsg.await())
            }
            sendMsgFromApi(mailId.await(), mailMsg.await())
        }
        log.info("Done Sending msg " + msg.await())
        async(UI) {
            sent_success_text_view.text = String.format(getString(R.string.msg_success)
                    , msg.await())
            progress_bar.visibility = View.GONE
        }
    }

    private suspend fun sendMsgFromApi(mailId: String, msg: String): String {
        //send from API
        delay(2000)
        return msg + " to " + mailId
    }

    private suspend fun getMailFromDB(): String {
        delay(3000)
        //query from DB will be here
        log.info("Got Mail ID from DB")
        return "amanjeetsingh150@gmail.com"
    }

    private suspend fun getMessageFromDB(): String {
        delay(3000)
        //query from DB will be here
        log.info("Got Msg from DB")
        return "This is an example for coroutines"
    }


}
