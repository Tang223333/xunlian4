package com.lenovo.manufacture.hxf.Utils;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;

import com.lenovo.manufacture.hxf.MyActivity.Hxf_NetworkDataRequestActivity;

import java.util.HashMap;

public class MainApplication extends Application {
    //声明一个当前应用的静态实例
    private static MainApplication mApp;
    //声明一个公共的信息映射，可当做全局变量使用
    public HashMap<String,String> mInfoMap = new HashMap<>();

    /**
     * 判断是否为第一次启动APP
     * @return isOneStartApp
     */
    public static boolean isOneStartApp() {
        SharedPreferences sharedPreferences = getInstance().getSharedPreferences("isOneStartApp",0);
        if (sharedPreferences.getBoolean("isOne",true)){
            sharedPreferences.edit().putBoolean("isOne",false).apply();
            Intent intent = new Intent(getInstance(), Hxf_NetworkDataRequestActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
            getInstance().startActivity(intent);
            return true;
        }
        return sharedPreferences.getBoolean("isOne",false);
    }

    //利用单例模式获取当前应用的唯一实例
    public static MainApplication getInstance(){
        return mApp;
    }
    public static boolean getIsOneStartApp(){
        return isOneStartApp();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
}
