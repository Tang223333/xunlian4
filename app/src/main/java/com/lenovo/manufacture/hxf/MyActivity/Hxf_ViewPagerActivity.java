package com.lenovo.manufacture.hxf.MyActivity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.PagerTitleStrip;
import androidx.viewpager.widget.ViewPager;

import com.lenovo.manufacture.BuildConfig;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hxf.Utils.CustomVideoView;
import com.lenovo.manufacture.hxf.Utils.GetPhoneInfo;
import com.lenovo.manufacture.hxf.adapter.MyViewPagerAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class Hxf_ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener, MediaPlayer.OnCompletionListener {

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
    private Handler handler = new Handler();
    private VideoView videoView;
    private List<View> viewList;
    private List<String> stringList;
    private LinearLayout layout_viewPager_father;
    private MediaPlayer mediaPlayer;
    private PagerTabStrip mViewPagerTab;
    private PagerTitleStrip mViewPagerTitle;
    private TextView tv_viewPager01;
    private TextView tv_viewPager02;
    private TextView tv_viewPager03;
    private List<View> tabViewList;
    private LinearLayout layout_topTab;
    private FrameLayout layout_dialogVideo_father;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hxf_view_pager);
        initView();
        initViewPager();
        initTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
        mediaPlayer.reset();
        mediaPlayer.release();
    }

    private void initTimer() {
        timer = new Timer();
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

        tabViewList = new LinkedList<>();
        tv_viewPager01 = findViewById(R.id.tv_viewPager01);
        tv_viewPager02 = findViewById(R.id.tv_viewPager02);
        tv_viewPager03 = findViewById(R.id.tv_viewPager03);

        tabViewList.add(tv_viewPager01);
        tabViewList.add(tv_viewPager02);
        tabViewList.add(tv_viewPager03);

        layout_topTab = findViewById(R.id.layout_topTab);
        layout_topTab.removeAllViews();
        layout_topTab.addView(tv_viewPager01);
        layout_topTab.addView(tv_viewPager02);
        layout_topTab.addView(tv_viewPager03);

        String[] stringArray = getResources().getStringArray(R.array.viewPagerTab);

        tv_viewPager01.setText(stringArray[0]);
        tv_viewPager02.setText(stringArray[1]);
        tv_viewPager03.setText(stringArray[2]);

        stringList.add(stringArray[0]);
        stringList.add(stringArray[1]);
        stringList.add(stringArray[2]);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(viewList, stringList);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(this);

        //TODO 设置当前页码，即打开翻页视图时默认显示哪一个页面
        viewPager.setCurrentItem(0);

//        mViewPagerTitle = (PagerTitleStrip) findViewById(R.id.viewPager_title);

//        mViewPagerTab = (PagerTabStrip) findViewById(R.id.viewPager_tab);
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
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ge);
        videoView = new VideoView(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.hxf_custom_dialog, null, false);
        customDialogShow(inflate, R.mipmap.icon, getString(R.string.dialog_title), getString(R.string.dialog_content));
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
        if (!dialog_title.isEmpty()) {
            mTvDialogTitle.setText(dialog_title);
        }
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
        for (int i = 0; i < tabViewList.size(); i++) {
            TextView view = (TextView) tabViewList.get(i);
            view.setTextColor(Color.rgb(3, 168, 244));
            view.setBackgroundColor(Color.WHITE);
        }
        TextView textView = (TextView) tabViewList.get(position);
        textView.setTextColor(Color.rgb(255, 87, 34));
        textView.setBackgroundColor(Color.rgb(187, 187, 187));

//        //TODO  设置viewPagerTitleStrip的文字大小
//        mViewPagerTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//        //TODO  设置viewPagerTitleStrip的文字颜色
//        mViewPagerTitle.setTextColor(Color.WHITE);

//        //TODO  设置viewPagerTabStrip的文字大小
//        mViewPagerTab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
//        //TODO  设置viewPagerTabStrip的文字颜色
//        mViewPagerTab.setTextColor(Color.WHITE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_playMusic:
                playMusic();
                if (!dialog.isShowing()) {
                    View inflate = LayoutInflater.from(this).inflate(R.layout.hxf_custom_dialog, null, false);
                    customDialogShow(inflate, R.mipmap.icon, getString(R.string.dialog_title), getString(R.string.dialog_content));
                }
                break;
            case R.id.btn_startFlip:
                timer.cancel();
                removeAllView();
                layout_viewPager_father.addView(layout_topTab);
                layout_viewPager_father.addView(viewPager);
                initViewPager();
                initTimer();
                break;
            case R.id.btn_stopFlip:
                timer.cancel();
                break;
            case R.id.btn_playVideo:
//                layout_viewPager_father.setPadding(45, 60, 45, 60);
                removeAllView();
                videoView = new CustomVideoView(this);
                videoView.setOnCompletionListener(this);
//                layout_viewPager_father.addView(videoView);

                //TODO 对话框式视频播放器
                View inflate = LayoutInflater.from(this).inflate(R.layout.hxf_blank_dialog, null, false);
                customDialogShow(inflate,0,"","");
                layout_dialogVideo_father = inflate.findViewById(R.id.layout_dialogVideo_father);
                layout_dialogVideo_father.removeAllViews();
                layout_dialogVideo_father.addView(videoView);

                //TODO 设置视频文件
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pm));
                //TODO 设置控制条
                videoView.setMediaController(new MediaController(this));
                videoView.start();
                break;
            case R.id.btn_dialog_negative:
                dialog.dismiss();
                if (layout_dialogVideo_father != null) {
                    rePlayVideo();
                }
                Toast.makeText(this, "已取消！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dialog_neutral:
                dialog.dismiss();
                if (layout_dialogVideo_father != null) {
                    rePlayVideo();
                }
                Toast.makeText(this, "已忽略！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_dialog_positive:
                dialog.dismiss();
                if (layout_dialogVideo_father != null) {
                    rePlayVideo();
                }
                Toast.makeText(this, "已确定！", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    private void playMusic() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
//                    mBtnPlayMusic.setText("暂停播放");
            mBtnPlayMusic.setBackgroundResource(R.mipmap.play);
        } else {
            mediaPlayer.pause();
//                    mBtnPlayMusic.setText("播放音乐");
            mBtnPlayMusic.setBackgroundResource(R.mipmap.timeout);
        }
    }

    private void removeAllView() {
        layout_viewPager_father.removeAllViews();
//        layout_viewPager_father.removeView(viewPager);
//        layout_viewPager_father.removeView(videoView);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (dialog.isShowing()) {
            dialog.dismiss();
            rePlayVideo();
        }
    }

    private void rePlayVideo() {
        removeAllView();
        layout_dialogVideo_father.removeAllViews();
//        layout_viewPager_father.setPadding(45, 60, 45, 60);
        layout_viewPager_father.addView(videoView);
        //TODO 设置视频文件
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.pm));
        //TODO 设置控制条
        videoView.setMediaController(new MediaController(this));
        videoView.start();
    }
}
