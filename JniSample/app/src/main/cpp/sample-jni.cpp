//
// Created by nakama on 09/02/19.
//

#include <jni.h>
#include "sample-jni.h"


extern "C"
JNIEXPORT jint JNICALL

Java_com_pramonow_androidadvanced_JniActivity_getKey(JNIEnv* pEnv, jobject instance, jint key1) {
    return key1+200;
}
