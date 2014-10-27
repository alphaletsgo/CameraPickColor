/**
 * Name        : ImageUtilEngine.java
 * Copyright   : Copyright (c) Tencent Inc. All rights reserved.
 * Description : TODO
 */

package com.isvsa.jni;


/**
 * @author zh
 */
public class ImageUtilEngine {

    static {
        System.loadLibrary("JNIRGB");
    }

    public native int[] decodeYUV420SP(byte[] buf, int width, int heigth);
}
