package com.lenovo.manufacture.hxf.MyActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.lenovo.manufacture.BuildConfig;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hxf.Utils.GetPhoneInfo;
import com.lenovo.manufacture.hxf.adapter.MyViewPagerAdapter;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Hxf_ViewPagerActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TextView phone_Info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hxf_view_pager);
        initView();
        List<View> viewList = new LinkedList<>();

        viewList.add(LayoutInflater.from(this).inflate(R.layout.hxf_viewpager_01,null,false));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.hxf_viewpager_02,null,false));
        viewList.add(LayoutInflater.from(this).inflate(R.layout.hxf_viewpager_03,null,false));

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(viewList);
        viewPager.setAdapter(myViewPagerAdapter);
        //TODO 设置当前页码，即打开翻页视图时默认显示哪一个页面
        viewPager.setCurrentItem(1);

    }
    public void getPhoneInfo(Context context) {
        Map<String, Object> windowParameter = GetPhoneInfo.getWindowParameter(context);
        String s = windowParameter.toString();
        phone_Info.setText(context.getString(R.string.phone_Info,
                windowParameter.get("density"),windowParameter.get("width"),windowParameter.get("height")));
        if (BuildConfig.DEBUG) Log.d("Hxf_ViewPagerActivity", s);
    }

    private void initView() {
        phone_Info = findViewById(R.id.phone_Info);
        getPhoneInfo(this);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }
}
