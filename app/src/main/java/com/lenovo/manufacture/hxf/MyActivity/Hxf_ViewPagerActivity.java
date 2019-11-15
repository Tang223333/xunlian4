package com.lenovo.manufacture.hxf.MyActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.lenovo.manufacture.BuildConfig;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hxf.Utils.GetPhoneInfo;
import com.lenovo.manufacture.hxf.Utils.log;
import com.lenovo.manufacture.hxf.Utils.Toasts;
import com.lenovo.manufacture.hxf.adapter.MyViewPagerAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Hxf_ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager viewPager;
    private TextView phone_Info;
    private Timer timer;
    private Button mBtnStartFlip;
    private Button mBtnStopFlip;
    private Button mBtnPlayMusic;
    private Button mBtnPlayVideo;
    private Dialog dialog;
    private ImageView mIvDialogIcon;
    private TextView mTvDialogTitle;
    private TextView mTvDialogContent;
    private Button mBtnDialogNegative;
    private Button mBtnDialogNeutral;
    private Button mBtnDialogPositive;
    private int currentItem; //TODO 初始化游标(当前展示的viewPager的序号)
    Handler handler = new Handler();
    private VideoView videoView;
    private List<View> viewList;
    private List<String> stringList;
    private LinearLayout layout_viewPager_father;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hxf_view_pager);
        initView();

        log.d(getLocalClassName(), "。。。");
        Toasts.makeText(this, "。。", Toasts.LENGTH_LONG);
        initViewPager();
        initTimer();
    }

    private void initTimer() {
        timer = new Timer();
        log.d(getLocalClassName(), "....");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //TODO viewPager实现轮播效果的关键算法
                currentItem = (currentItem + 1) % viewList.size();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(currentItem);
                    }
                });
            }
        }, 0, 2000);
    }

    private void initViewPager() {
        layout_viewPager_father.setPadding(0, 0, 0, 0);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewList = new LinkedList<>();
        viewList.add(LayoutInflater.from(this).inflate(R.layout.hxf_viewpager_01, null, false));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.hxf_viewpager_02, null, false));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.hxf_viewpager_03, null, false));
        stringList = new LinkedList<>();
        stringList.add("第一页");
        stringList.add("第二页");
        stringList.add("第三页");
        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(viewList, stringList);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        //TODO 设置当前页码，即打开翻页视图时默认显示哪一个页面
        viewPager.setCurrentItem(0);
    }

    public void getPhoneInfo(Context context) {
        Map<String, Object> windowParameter = GetPhoneInfo.getWindowParameter(context);
        String s = windowParameter.toString();
        phone_Info.setText(context.getString(R.string.phone_Info,
                windowParameter.get("density"), windowParameter.get("width"), windowParameter.get("height")));
        if (BuildConfig.DEBUG) Log.d("Hxf_ViewPagerActivity", s);
    }

    private void initView() {
        phone_Info = findViewById(R.id.phone_Info);
        getPhoneInfo(this);
        mBtnPlayMusic = (Button) findViewById(R.id.btn_playMusic);
        mBtnPlayMusic.setOnClickListener(this);
        mBtnStartFlip = (Button) findViewById(R.id.btn_startFlip);
        mBtnStartFlip.setOnClickListener(this);
        mBtnStopFlip = (Button) findViewById(R.id.btn_stopFlip);
        mBtnStopFlip.setOnClickListener(this);
        mBtnPlayVideo = (Button) findViewById(R.id.btn_playVideo);
        mBtnPlayVideo.setOnClickListener(this);
        layout_viewPager_father = findViewById(R.id.layout_viewPager_father);
        layout_viewPager_father.setBackgroundColor(Color.rgb(0, 0, 0));
        initViewPager();
        View inflate = LayoutInflater.from(this).inflate(R.layout.hxf_custom_dialog, null, false);
        customDialogShow(inflate, R.mipmap.ic_launcher, "今日头疼", "今天我的头疼得很呐！");

    }

    private void initDialog(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(view);
        dialog.getWindow().findViewById(R.id.layout);
        mIvDialogIcon = (ImageView) dialog.getWindow().findViewById(R.id.iv_dialog_icon);
        mTvDialogTitle = (TextView) dialog.getWindow().findViewById(R.id.tv_dialog_title);
        mTvDialogContent = (TextView) dialog.getWindow().findViewById(R.id.tv_dialog_content);
        mBtnDialogNegative = (Button) dialog.getWindow().findViewById(R.id.btn_dialog_negative);
        mBtnDialogNeutral = (Button) dialog.getWindow().findViewById(R.id.btn_dialog_neutral);
        mBtnDialogPositive = (Button) dialog.getWindow().findViewById(R.id.btn_dialog_positive);
    }

    private void customDialogShow(View layout, int dialog_icon, String dialog_title, String dialog_content) {
        initDialog(layout);
        if (dialog_icon != 0) {
            mIvDialogIcon.setBackgroundResource(dialog_icon);
        }
        mTvDialogTitle.setText(dialog_title);
        if (!dialog_content.isEmpty()) {
            mTvDialogContent.setText(dialog_content);
        }
        //TODO Negative消极的
        mBtnDialogNegative.setOnClickListener(this);
        //TODO Neutral中立的
        mBtnDialogNeutral.setOnClickListener(this);
        //TODO Positive积极的
        mBtnDialogPositive.setOnClickListener(this);
        dialog.setCancelable(true);
        dialog.show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentItem = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_playMusic:
                View inflate = LayoutInflater.from(this).inflate(R.layout.hxf_custom_dialog, null, false);
                customDialogShow(inflate,R.mipmap.ic_launcher, "今日头疼", "今天我的头疼得很呐！");
                break;
            case R.id.btn_startFlip:
                timer.cancel();
                try {
                    layout_viewPager_father.addView(viewPager);
                } catch (Exception e) {
                    e.printStackTrace();
                    log.d("我知道这里会有一个异常，但是我无法解决！\n",e.toString());
                }
                layout_viewPager_father.removeView(videoView);
                initViewPager();
                initTimer();
                break;
            case R.id.btn_stopFlip:
                timer.cancel();
                break;

            case R.id.btn_playVideo:
                videoView = new VideoView(this);
                layout_viewPager_father.setPadding(5, 40, 5, 40);
                layout_viewPager_father.removeView(viewPager);
                layout_viewPager_father.addView(videoView);
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pm));
                videoView.setMediaController(new MediaController(this));
                videoView.start();
                break;
            case R.id.btn_dialog_negative:
                dialog.dismiss();
                Toast.makeText(this, "已取消！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dialog_neutral:
                dialog.dismiss();
                Toast.makeText(this, "已忽略！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dialog_positive:
                dialog.dismiss();
                Toast.makeText(this, "已确定！", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
