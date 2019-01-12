package com.developers.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

/**
 * Created by Amanjeet Singh on 13/11/17.
 */
class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint()
    private val oval = RectF()
    private var startAngle = 120f
    private var sweepAngle = 150f
    private var runnable: Runnable = object : Runnable {
        override fun run() {

            if (startAngle <= 360) {
                startAngle += 10
            } else {
                startAngle = 10f
            }
            invalidate();
            postDelayed(this, 30);
        }
    }

    init {
        post(runnable)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.BLUE
        paint.isAntiAlias = true
        paint.strokeWidth = 7f
        paint.style = Paint.Style.STROKE
        oval.set(width / 2 - 50f, height / 2 - 50f, width / 2 + 50f, height / 2 + 50f)
        canvas?.drawArc(oval, startAngle, sweepAngle, false, paint)
    }


}