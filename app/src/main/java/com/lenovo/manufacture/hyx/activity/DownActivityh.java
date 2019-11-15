package com.lenovo.manufacture.hyx.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lenovo.manufacture.R;

public class DownActivityh extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {



    private SwipeRefreshLayout mSwiper;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_activityh);
        initView();
    }

    @SuppressLint({"ResourceAsColor", "ResourceType"})
    private void initView() {
        mSwiper = (SwipeRefreshLayout) findViewById(R.id.swiper);
        mSwiper.setOnRefreshListener(this);//设置下拉监听
        mSwiper.setColorSchemeColors(Color.RED, Color.BLACK,
                Color.YELLOW, Color.BLUE);//设置图标刷新中的颜色
    }

    @Override
    public void onRefresh() {
        Toast.makeText(DownActivityh.this, "正在刷新", Toast.LENGTH_SHORT).show();
        handler.postDelayed(runnable,5000);
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mSwiper.setRefreshing(false);//设置图标隐藏
            Toast.makeText(DownActivityh.this, "刷新成功", Toast.LENGTH_SHORT).show();
        }
    };
}
