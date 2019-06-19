package com.developers.emojicompat

import android.app.Application
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import androidx.core.provider.FontRequest
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
        val config = FontRequestEmojiCompatConfig(this, fontRequest)
        EmojiCompat.init(config)
    }
}