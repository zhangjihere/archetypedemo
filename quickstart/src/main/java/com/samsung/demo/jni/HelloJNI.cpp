#include <jni.h>
#include <stdio.h>
#include "com_samsung_demo_jni_HelloJNI.h"

// Implementation of native method sayHello() of HelloJNI class
JNIEXPORT void JNICALL Java_com_samsung_demo_jni_HelloJNI_sayHello(JNIEnv *env, jobject thisObj) {
   printf("Hello World! I am JNI.\n");
   return;
}