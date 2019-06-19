package com.developers.emojicompat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.emoji.text.EmojiCompat
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val WOMAN_TECHNOLOGIST = "\uD83D\uDC69\u200D\uD83D\uDCBB"
    private val WOMAN_SINGER = "\uD83D\uDC69\u200D\uD83C\uDFA4"
    private val emojiContent: String = WOMAN_TECHNOLOGIST + " " + WOMAN_SINGER

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ok_button.setOnClickListener {
            EmojiCompat.get().registerInitCallback(object : EmojiCompat.InitCallback() {
                override fun onInitialized() {
                    super.onInitialized()
                    Log.d("MainActivity", "EmojiCompat initialized successfully")
                    val processed = EmojiCompat.get().process(emojiContent)
                    emoji_text_view.text = processed
                }

                override fun onFailed(throwable: Throwable?) {
                    super.onFailed(throwable)
                    Toast.makeText(this@MainActivity,
                            throwable?.message ?: "", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
