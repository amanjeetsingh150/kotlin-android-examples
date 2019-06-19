package com.developers.sharedelementtransition

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        card_view.setOnClickListener {
            val intent = Intent(this, TransitionActivity::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptionsCompat
                        .makeSceneTransitionAnimation(this,
                                card_view as View, getString(R.string.transition_string))
                startActivity(intent, options.toBundle())
            } else {
                startActivity(intent)
            }
        }

    }
}
