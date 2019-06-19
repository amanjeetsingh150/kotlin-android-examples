package com.jatin.mobilevisionbarcode

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.SurfaceView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var cameraView: SurfaceView
    internal lateinit var barcodeInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        cameraView = findViewById(R.id.camera_view)
        barcodeInfo = findViewById(R.id.code_info)

        MobileVisionHelper.init(this, cameraView, object : CodeListener {
            override fun onDetected(data: String) {
                barcodeInfo.post { barcodeInfo.text = data }
            }
        })

    }

}

