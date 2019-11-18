package com.lenovo.manufacture.cy.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.cy.adapter.CyFragmentAdapter;
import com.lenovo.manufacture.cy.fragment.CyFragment1Fragment;
import com.lenovo.manufacture.cy.fragment.CyFragment2Fragment;
import com.lenovo.manufacture.cy.fragment.CyFragment3Fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class cyActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {

    private RadioButton radioButton;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private ViewPager vp;
    private RadioGroup rg;
    private List<Fragment> list=new ArrayList<Fragment>();
    private MediaPlayer player;
    private Timer timer=new Timer();
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cy);
        initView();
    }



    private void initView() {
        radioButton = (RadioButton) findViewById(R.id.radioButton);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);
        list.add(new CyFragment1Fragment());
        list.add(new CyFragment2Fragment());
        list.add(new CyFragment3Fragment());
        vp = (ViewPager) findViewById(R.id.vp);
        vp.setAdapter(new CyFragmentAdapter(getSupportFragmentManager(),list));
        vp.setOnPageChangeListener(this);
        player=MediaPlayer.create(getApplicationContext(),R.raw.ge);
        player.start();
        if (!player.isPlaying()){
            player.reset();
            player.release();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(radioButton2.isChecked()){
                            radioButton3.performClick();
                        }else
                        if(radioButton3.isChecked()){
                            radioButton.performClick();
                        }else
                        if(radioButton.isChecked()){
                            radioButton2.performClick();
                        }
                    }
                });
            }
        },0,1400);
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.release();
        timer.cancel();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.radioButton:
                //选择fragment替换的部分（在Fragment控件中显示出来）
                //getSupportFragmentManager().beginTransaction().replace(R.id.vp,new CyFragment1Fragment()).commit();
                vp.setCurrentItem(0);
                break;
            case R.id.radioButton2:
                vp.setCurrentItem(1);
                break;
            case R.id.radioButton3:
                vp.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {//viewpager改变当前页面的时候的时候触发
        switch (position){
            case 0:
                radioButton.performClick();
                break;
            case 1:
                radioButton2.performClick();
                break;
            case 2:
                radioButton3.performClick();
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
