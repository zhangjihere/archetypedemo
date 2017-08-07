package com.samsung.demo.jni;

/**
 * Created by ji.zhang on 8/7/17.
 */
public class HelloJNI {
    static {
        System.loadLibrary("hello"); // Load native library at runtime
        // hello.dll (Windows) or libhello.so (Unixes)
    }

    // Declare a native method sayHello() that receives nothing and returns void
    private native void sayHello();

    // Test Main method
    public static void main(String[] args) {
        new HelloJNI().sayHello();  // invoke the native method
    }
}
