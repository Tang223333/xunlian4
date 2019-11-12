package com.lenovo.manufacture.hyx;

import android.app.Application;

import java.util.HashMap;

public class MyApplication extends Application {
    private static MyApplication myApplication;
    public HashMap<String,String> hashMap = new HashMap<String, String>();

    public static MyApplication getInstance(){
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication= this;
    }
}
