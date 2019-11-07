package com.lenovo.manufacture;

import android.os.Bundle;
import android.os.Handler;
import android.widget.AbsListView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.cy.adapter.CyListAdapter;
import com.lenovo.manufacture.cy.adapter.Demo;
import com.lenovo.manufacture.hxf.Utils.MyOkHttp;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main4Activity extends AppCompatActivity {
    private ListView cy_lv1;//陈宇
    private List<Demo> list=new ArrayList<Demo>();
    private Timer timer=new Timer();
    private Handler handler=new Handler();
    private JSONObject object;
    private int pm25;
    private int co2;
    private int light;
    private int hum;
    private int tem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        initView();
    }

    private void initView() {
        cy_lv1 = (ListView) findViewById(R.id.cy_lv1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                postdata();
            }
        },0,1000);
    }

    @Override
    protected void onPause() {
        timer.cancel();
        super.onPause();
    }

    private void postdata() {
        try {
            object = MyOkHttp.postData(this,"/transportservice/action/GetAllSense.do","{\"UserName\":\"user1\"}");
            pm25 = object.getInt("pm2.5");
            co2 = object.getInt("co2");
            light = object.getInt("LightIntensity");
            hum = object.getInt("humidity");
            tem = object.getInt("temperature");
        }catch (Exception e){

        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                list.add(new Demo(pm25,co2,light,hum+"::"+tem));
                cy_lv1.setAdapter(new CyListAdapter(Main4Activity.this,list));
            }
        });
    }
}
