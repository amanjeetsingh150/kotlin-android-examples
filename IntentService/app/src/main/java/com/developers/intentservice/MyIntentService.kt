package com.developers.intentservice

import android.app.IntentService
import android.content.Intent
import java.util.logging.Logger


/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions and extra parameters.
 */
class MyIntentService : IntentService("MyIntentService") {


    val log = Logger.getLogger(MyIntentService::class.java.name)

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val data = intent.getStringExtra("data")
            log.info("Message is : ${data}")
        }
    }


}
