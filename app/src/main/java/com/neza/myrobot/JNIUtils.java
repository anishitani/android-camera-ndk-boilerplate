package com.neza.myrobot;

import android.graphics.ImageFormat;
import android.media.Image;
import android.media.Image.Plane;
import android.view.Surface;

import java.nio.ByteBuffer;

public class JNIUtils {
    private static final String TAG = "JNIUtils";

    // Load native shared library.
    static {
        System.loadLibrary("native");
    }

    /**
     * Use native code to copy the contents of src to dst. src must have format
     * YUV_420_888, dst must be YV12 and have been configured with
     * {@code configureSurface()}.
     */
    public static void detectLane(Image src, Surface dst) {

        if (src.getFormat() != ImageFormat.YUV_420_888) {
            throw new IllegalArgumentException("src must have format YUV_420_888.");
        }
        Plane[] planes = src.getPlanes();
        // Spec guarantees that planes[0] is luma and has pixel stride of 1.
        // It also guarantees that planes[1] and planes[2] have the same row and
        // pixel stride.
        if (planes[1].getPixelStride() != 1 && planes[1].getPixelStride() != 2) {
            throw new IllegalArgumentException(
                    "src chroma plane must have a pixel stride of 1 or 2: got "
                    + planes[1].getPixelStride());
        }

        detectLane(src.getWidth(), src.getHeight(), planes[0].getBuffer(),dst);
    }

    private static native void detectLane(int srcWidth,
                                            int srcHeight,
                                            ByteBuffer srcBuf,
                                            Surface dst);
}
