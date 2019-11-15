package com.lenovo.manufacture.hxf.Utils;

import android.util.Log;

/**
 * 自定义Log日志，适用于经常打印日志的程序猿
 */
public class log {
    public static void d(String TAG, String msg){
        Log.d(TAG, "心情美美哒！ ==========" + msg);
    }
}
