package com.developers.runtimepermissions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    companion object {

    }

    private val REQUEST_CODE_PERMISSION: Int = 200
    var accept_call: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        call_button.setOnClickListener({
            makeCall()
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            accept_call = grantResults[0] == PackageManager.PERMISSION_GRANTED
            if (accept_call) {
                toast("Permission for call Granted")
                makeCall()
            } else {
                toast("Permission for call required")
            }
        }
    }

    private fun makeCall() {
        val intent: Intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:9716052812")
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.CALL_PHONE),
                    REQUEST_CODE_PERMISSION)
        } else {
            startActivity(intent)
        }

    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }


}
