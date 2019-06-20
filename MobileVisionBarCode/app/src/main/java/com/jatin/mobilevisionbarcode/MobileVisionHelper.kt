package com.jatin.mobilevisionbarcode

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.io.IOException

object MobileVisionHelper {

    private var barcodeDetector: BarcodeDetector? = null
    private var cameraSource: CameraSource? = null

    fun init(context: Context, cameraView: SurfaceView, codeListener: CodeListener) {
        barcodeDetector = BarcodeDetector.Builder(context)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build()

        cameraSource = CameraSource.Builder(context, barcodeDetector)
                .setRequestedPreviewSize(800, 800)
                .build()

        cameraView.holder
                .addCallback(object : SurfaceHolder.Callback {
                    override fun surfaceCreated(holder: SurfaceHolder) {
                        startCameraView(context, cameraSource, cameraView)
                    }

                    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

                    override fun surfaceDestroyed(holder: SurfaceHolder) {
                        startCameraView(context, cameraSource, cameraView)
                    }
                })

        barcodeDetector!!.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {}

            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                val barcodes = detections.getDetectedItems()

                if (barcodes.size() != 0) {
                    System.out.println("Value : " + barcodes.valueAt(0).displayValue)
                    codeListener.onDetected(barcodes.valueAt(0).displayValue)
                }
            }
        })

    }

    private fun startCameraView(context: Context, cameraSource: CameraSource?, cameraView: SurfaceView) {
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                cameraSource!!.start(cameraView.holder)
            } else {
                println("Permission not granted!")
            }
        } catch (ie: IOException) {
            Log.e("CAMERA SOURCE", ie.message)
        }

    }
}

