package com.pramonow.androidadvanced

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class JniActivity:AppCompatActivity(){

    //Our Jni method
    external fun getKey(key:Int):Int

    companion object {
        init {
            System.loadLibrary("sample-jni")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jni)

        Toast.makeText(this,getKey(55).toString(),Toast.LENGTH_SHORT).show()
    }

}