# Jni android sample

JNI stands for Java Native Interface. This sample is showing how to call native c/c++ method to be used inside android.
In order to run this sample, you need to install <b>NDK</b> in android SDK manager beforehand.

If you looked through, the project contains c/c++ file which contains:

    #include <jni.h>
    #include "sample-jni.h"


    extern "C"
    JNIEXPORT jint JNICALL

    Java_com_pramonow_androidadvanced_JniActivity_getKey(JNIEnv* pEnv, jobject instance, jint key1) {
        return key1+200;
    }
    
And the getKey function will be able to be called inside the activity:

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

        Toast.makeText(this,getKey(55).toString(),Toast.LENGTH_SHORT).show() // will show 255
    }
