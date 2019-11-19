package com.lenovo.manufacture.thl;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.lenovo.manufacture.R;

public class Thl_7Activity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private AppBarLayout thl7Abl;
    private CollapsingToolbarLayout thl7Ctl;
    private Toolbar thl7Tb;
    private NestedScrollView thl6Nsv;
    private TextView text1;
    private TextView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thl_7);
        initView();
    }

    private void initView() {
        thl7Abl = (AppBarLayout) findViewById(R.id.thl7_abl);
        thl7Ctl = (CollapsingToolbarLayout) findViewById(R.id.thl7_ctl);
        thl7Tb = (Toolbar) findViewById(R.id.thl7_tb);
        thl6Nsv = (NestedScrollView) findViewById(R.id.thl6_nsv);

        setSupportActionBar(thl7Tb);

        //给太黑了Thl_abl注册一个位置偏移的监听器
        thl7Abl.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) this);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
//        int offset = Math.abs(i);//i滑动的值 i永远小于等于0
        int offset= i*(-1);
        //获取应用栏的整个滑动范围，以此计算当前的位置比例
        int total = appBarLayout.getTotalScrollRange();//获取AppBarLayout最大的滑动距离
        Toast.makeText(this, "offset" + offset + "  i" + i + "   total" + total, Toast.LENGTH_SHORT).show();
        if (offset > 0) {
            Drawable drawable = getResources().getDrawable(R.drawable.divider_red2);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            thl7Tb.setBackground(drawable);
            text1.setVisibility(View.VISIBLE);
            text2.setVisibility(View.GONE);
        } else {
            Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            thl7Abl.setBackground(drawable);
            thl7Tb.setBackground(drawable);
            text1.setVisibility(View.GONE);
            text2.setVisibility(View.VISIBLE);
        }
    }
}
