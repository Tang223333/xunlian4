package com.lenovo.manufacture.hxf.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hxf.Utils.MyOkHttp;
import com.lenovo.manufacture.hxf.Utils.ShowToast;
import com.lenovo.manufacture.hxf.Utils.log;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    private Timer timer;
    private JSONObject result;
    private Handler handler = new Handler();
    private String resultSTATE;
    private MediaPlayer mediaPlayer;
    public static boolean SERVICE_STATE = false;

    public MyService() {
    }

    //TODO 开启服务时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log.d("onStartCommand====", "");
        startTimer();
        //TODO 设置音频资源文件
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bgm);
        //TODO 开始播放bgm
        mediaPlayer.start();
        SERVICE_STATE = true;
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

    //TODO 停止服务时调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        mediaPlayer.reset();
        mediaPlayer.release();
        SERVICE_STATE = false;
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
                    resultSTATE = result.getString("ERRMSG");
                } catch (Exception e) {
                    e.printStackTrace();
                    log.d("=============", e.toString());
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (result != null) {
                            if (resultSTATE!=null){
                                if (resultSTATE.equals("失败")){
                                    ShowToast.show(getApplicationContext(), "数据获取错误！");
                                }else if (resultSTATE.equals("成功")){
                                    ShowToast.show(getApplicationContext(), result.toString());
                                }
                            }
                        }else {
                            ShowToast.show(getApplicationContext(), "没有获取到数据！");
                        }
                    }
                });
            }
        }, 0, 100);
    }
}
