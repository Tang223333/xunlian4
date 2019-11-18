package com.lenovo.manufacture;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.hxf.MyActivity.Hxf_NetworkDataRequestActivity;
import com.lenovo.manufacture.hxf.MyActivity.Hxf_ViewPagerActivity;
import com.lenovo.manufacture.hxf.service.MyService;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_start_hxfActivity;
    private TextView textView;
    private LinearLayout layout_root;
    private TextView tv_startViewPager;
    private TextView tv_start_stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initView();
        //TODO 启动服务
        startService(new Intent(this, MyService.class));
    }

    @SuppressLint("ResourceType")
    private void initView() {
        layout_root = findViewById(R.id.layout_root);
        layout_root.setGravity(Gravity.CENTER);
        tv_start_hxfActivity = findViewById(R.id.tv_start_hxfActivity);
        tv_start_hxfActivity.setOnClickListener(this);
        textView = new TextView(this);
        textView.setBackgroundColor(Color.argb(100, 130, 202, 247));
        textView.setOnClickListener(this);
        textView.setId(0x0003);
        addContentView(textView, new LinearLayout.LayoutParams(Hxf_NetworkDataRequestActivity.Width,
                Hxf_NetworkDataRequestActivity.Height));
        tv_startViewPager = findViewById(R.id.tv_startViewPager);
        tv_startViewPager.setOnClickListener(this);
        tv_start_stopService = findViewById(R.id.tv_start_stopService);
        tv_start_stopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case 0x0003:
                startActivity(new Intent(this, Hxf_NetworkDataRequestActivity.class));
                break;
            case R.id.tv_startViewPager:
                startActivity(new Intent(this, Hxf_ViewPagerActivity.class));
                //TODO 关闭服务
                stopService(new Intent(this, MyService.class));
                break;
            case R.id.tv_start_stopService:
                if (MyService.SERVICE_STATE) {
                    //TODO 关闭服务
                    stopService(new Intent(this, MyService.class));
                    tv_start_stopService.setText("开启服务");
                } else {
                    //TODO 开启服务
                    startService(new Intent(this, MyService.class));
                    tv_start_stopService.setText("关闭服务");
                }
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //TODO 启动服务
        startService(new Intent(this, MyService.class));
        tv_start_stopService.setText("关闭服务");
    }
}
