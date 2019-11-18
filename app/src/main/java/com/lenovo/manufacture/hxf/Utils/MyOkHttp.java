package com.lenovo.manufacture.hxf.Utils;

import android.content.Context;

import org.json.JSONObject;


import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MyOkHttp {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static JSONObject jsonObject;
    //    {"pm2.5":293,"co2":5160,"LightIntensity":1503,"humidity":34,"temperature":1,"RESULT":"S","ERRMSG":"成功"}
    public static JSONObject postData(Context context, String url, String json) throws Exception {

        String urls = "http://" + context.getSharedPreferences("IP",0)
                .getString("ip","192.168.124.4")
                +":" + 8088 + "/transportservice/action/"+url;
        Request request = new Request.Builder()
                .url(urls)
                .post(FormBody.create(MediaType.parse("application/json;utf-8"), json))
                .build();
//        return new JSONObject(new
//        OkHttpClient().newCall(request).execute().body().string());
        return jsonObject = new JSONObject(okHttpClient.newCall(request).execute().body().string());
    }
}
