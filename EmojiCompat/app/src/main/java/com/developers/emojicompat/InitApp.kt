package com.developers.emojicompat

import android.app.Application
import android.support.text.emoji.EmojiCompat
import android.support.text.emoji.FontRequestEmojiCompatConfig
import android.support.v4.provider.FontRequest
import java.util.logging.Logger

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
class InitApp : Application() {

    companion object {
        val Log = Logger.getLogger(InitApp::class.java.name)
    }

    override fun onCreate() {
        super.onCreate()
        val fontRequest = FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs)
//        val config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
//                .setReplaceAll(true)
//                .registerInitCallback(object : EmojiCompat.InitCallback() {
//                    override fun onInitialized() {
//                        Log.info("EmojiCompat initialized")
//                    }
//
//                    override fun onFailed(throwable: Throwable?) {
//                        Log.info("EmojiCompat initialization failed")
//                    }
//                })
        val config = FontRequestEmojiCompatConfig(this, fontRequest)
        EmojiCompat.init(config)
    }
}