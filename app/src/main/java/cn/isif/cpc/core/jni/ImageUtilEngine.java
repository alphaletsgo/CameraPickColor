/**
 * Name        : ImageUtilEngine.java
 * Copyright   : Copyright (c) Tencent Inc. All rights reserved.
 * Description : TODO
 */

package cn.isif.cpc.core.jni;


/**
 * @author zh
 */
public class ImageUtilEngine {

    static {
        System.loadLibrary("native-lib");
    }

    public native int[] decodeYUV420SP(byte[] buf, int width, int heigth);
}
