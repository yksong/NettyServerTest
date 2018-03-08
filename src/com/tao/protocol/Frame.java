package com.tao.protocol;

/**
 * Created by DXS on 2017/3/29.
 */
public class Frame {
    public static int frontFrame = 0;
    public static int currentFrame = 1;
    public static int[] uncompressData;

    public static int getFrontFrame() {
        return frontFrame;
    }

    public static void setFrontFrame(int frontFrame) {
        Frame.frontFrame = frontFrame;
    }

    public static int getCurrentFrame() {
        return currentFrame;
    }
    public static void setCurrentFrame(int currentFrame) {
        Frame.currentFrame = currentFrame;
    }

    public static int[] getUncompressData() {
        return uncompressData;
    }

    public static void setUncompressData(int[] uncompressData) {
        Frame.uncompressData = uncompressData;
    }
}
