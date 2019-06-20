package com.developers.objectanimator

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animate_button.setOnClickListener {
            val animator = ObjectAnimator.ofFloat(textView, "textSize", 12f)
            animator.duration = 800
            animator.start()
        }
    }
}
