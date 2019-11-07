package com.lenovo.manufacture.thl;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.frament.ThlFragment01Fragment;
import com.lenovo.manufacture.thl.frament.ThlFragment02Fragment;
import com.lenovo.manufacture.thl.frament.ThlFragment03Fragment;

import java.util.ArrayList;
import java.util.List;

public class TangHaiLong_01 extends AppCompatActivity {

    private ViewPager thlViewpager;
    List<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tang_hai_long_01);
        initView();
    }

    private void initView() {
        thlViewpager = (ViewPager) findViewById(R.id.thl_viewpager);
        list=new ArrayList<>();
        list.add(new ThlFragment01Fragment());
        list.add(new ThlFragment02Fragment());
        list.add(new ThlFragment03Fragment());
        FragmentPagerAdapter fragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };
        thlViewpager.setAdapter(fragmentPagerAdapter);
    }
}
