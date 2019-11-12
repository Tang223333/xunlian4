package com.lenovo.manufacture.hyx.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lenovo.manufacture.R;

public class DownActivityh extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private TextView mText;

    private SwipeRefreshLayout mSwiper;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_activityh);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        mText = (TextView) findViewById(R.id.text);
        mSwiper = (SwipeRefreshLayout) findViewById(R.id.swiper);
        mSwiper.setOnRefreshListener(this);
        mSwiper.setColorScheme(android.R.color.holo_blue_light, android.R.color.holo_green_light,
                android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        mText.setVisibility(View.VISIBLE);
        mText.setText("正在刷新");

        handler.postDelayed(runnable,2000);
    }
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mText.setText("刷新完成");

            mText.setVisibility(View.GONE);
            mSwiper.setRefreshing(false);
        }
    };
}
