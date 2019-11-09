package com.lenovo.manufacture.thl;

import android.app.Application;

import java.util.HashMap;

public class MainApplication extends Application {

    private static MainApplication mApp;

    public HashMap<String,String> mInfoMap=new HashMap<>();
    public HashMap<String, Boolean> mBoolean=new HashMap<>();

    //利用单例模式获取当前引用的唯一实例
    public static MainApplication getInstance(){
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;
    }
}
