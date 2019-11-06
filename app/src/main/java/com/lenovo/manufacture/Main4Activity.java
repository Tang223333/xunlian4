package com.lenovo.manufacture;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import com.lenovo.manufacture.hxf.Utils.MyOkHttp;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Main4Activity extends AppCompatActivity {
    private ListView cy_lv1;//陈宇
    private List<demo> list=new ArrayList<demo>();
    private Timer timer=new Timer();
    private Handler handler=new Handler();
    private JSONObject object;

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
            object = MyOkHttp.postData(this,"","");
        }catch (Exception e){

        }
        handler.post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }


    class demo{
        int a;
        int b;
        int c;
        String s;

        public demo(int a, int b, int c, String s) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.s = s;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public int getC() {
            return c;
        }

        public void setC(int c) {
            this.c = c;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }
    }
}
