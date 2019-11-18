package com.lenovo.manufacture.hxf.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import com.lenovo.manufacture.hxf.Utils.MyOkHttp;
import com.lenovo.manufacture.hxf.Utils.log;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private Timer timer;
    private JSONObject result;
    private Handler handler = new Handler();
    private String resultSTATE;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log.d("onStartCommand====", "");
        startTimer();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.(将通信通道返回到服务)

        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);

    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("UserName", "user1");
                    result = MyOkHttp.postData(getApplicationContext(), "GetAllSense.do", jsonObject.toString());
                    log.d("=====", result.toString());
                    resultSTATE = MyService.this.result.getString("ERRMSG");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.d("=============", e.toString());
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (result != null) {
                            if (resultSTATE.equals("失败")){
                                Toast.makeText(getApplicationContext(), "数据获取错误！", Toast.LENGTH_SHORT).show();
                            }else if (resultSTATE.equals("成功")){
                                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getApplicationContext(), "没有获取到数据！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }, 0, 100);
    }
}
