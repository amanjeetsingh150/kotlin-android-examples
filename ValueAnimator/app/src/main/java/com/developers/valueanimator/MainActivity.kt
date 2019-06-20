package com.developers.valueanimator

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val endValue = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animate_button.setOnClickListener {
            val valueAnimator = ValueAnimator.ofFloat(textView.textSize, endValue.toFloat())
            valueAnimator.duration = 600
            valueAnimator.addUpdateListener {
                val animatedValue = it.animatedValue as Float
                println(animatedValue)
                textView.textSize = animatedValue
            }
            valueAnimator.start()
        }
    }
}
