package com.developers.animatedvectordrawable

import android.graphics.drawable.Animatable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var animatable: Animatable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animatable = animatedImageView.drawable as Animatable
    }

    override fun onStart() {
        super.onStart()
        startButton.setOnClickListener {
            animatable.start()
        }
    }
}
