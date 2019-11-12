package com.lenovo.manufacture.hxf.Utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

public class GetPhoneInfo {

    /**
     * 获取设备屏幕的相关信息
     * @param context
     * @return Map<String, Object>
     */
    public static Map<String, Object> getWindowParameter(Context context) {
        WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        systemService.getDefaultDisplay().getMetrics(displayMetrics);
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("height", displayMetrics.heightPixels);
        hashMap.put("width", displayMetrics.widthPixels);
        hashMap.put("density", displayMetrics.density);
        return hashMap;
    }
}
