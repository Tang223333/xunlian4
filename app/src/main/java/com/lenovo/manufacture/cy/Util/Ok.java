package com.lenovo.manufacture.cy.Util;

import android.content.Context;

import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Ok {
    public static JSONObject Post(Context context,String url,String json) throws Exception{
        String strurl="http://"
                +context.getSharedPreferences("IP",0).getString("ip","192.168.124.4")
                +":8088/"+url;
        Request request=new Request.Builder()
                .post(FormBody.create(MediaType.parse("application/json;utf-8"),json))
                .url(strurl)
                .build();
        return new JSONObject(new OkHttpClient().newCall(request).execute().body().string());
    }
}
