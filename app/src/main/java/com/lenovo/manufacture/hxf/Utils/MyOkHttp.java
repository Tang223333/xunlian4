package com.lenovo.manufacture.hxf.Utils;

import android.content.Context;

import org.json.JSONObject;


import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MyOkHttp {
    public static JSONObject postData(Context context,String url,String json) throws Exception {
        String urls = "http://" + context.getSharedPreferences("IP",0)
                .getString("ip","192.168.124.4")
                +":" + 8088 + "/transportservice/action/"+url;
        Request request = new Request.Builder()
                .url(urls)
                .post(FormBody.create(MediaType.parse("application/json;utf-8"), json))
                .build();
        return new JSONObject(new OkHttpClient().newCall(request).execute().body().string());
    }
}
