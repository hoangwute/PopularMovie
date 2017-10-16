package com.wuochoang.popularmovie.common.utils;

import android.util.Log;

import com.wuochoang.popularmovie.common.Config;


/**
 * Created by DVC on 2/10/2017.
 */

public class LogUtils {
    public static boolean DEBUG = Config.DEBUG;
    private static final String TAG = "HRM";

    /**
     * @see Log#d(String, String)
     */
    public static void d(String message) {
        if (DEBUG)
            Log.d(TAG, buildMessage(message));
    }

    /**
     * @see Log#e(String, String)
     */
    public static void e(String message) {
        if (DEBUG)
            Log.e(TAG, buildMessage(message));
    }

    /**
     * @see Log#i(String, String)
     */
    public static void i(String message) {
        if (DEBUG)
            Log.i(TAG, buildMessage(message));
    }

    /**
     * @see Log#v(String, String)
     */
    public static void v(String message) {
        if (DEBUG)
            Log.v(TAG, buildMessage(message));
    }

    /**
     * @see Log#w(String, String)
     */
    public static void w(String message) {
        if (DEBUG)
            Log.w(TAG, buildMessage(message));
    }

    /**
     * @see Log#wtf(String, String)
     */
    public static void wtf(String message) {
        if (DEBUG)
            Log.wtf(TAG, buildMessage(message));
    }

    /**
     * @see Log#println(int, String, String)
     */
    public static void println(String message) {
        if (DEBUG)
            Log.println(Log.INFO, TAG, message);
    }

    private static String buildMessage(String rawMessage) {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String fullClassName = caller.getClassName();
        String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
        return className + "." + caller.getMethodName() + "(): " + rawMessage;
    }
}
