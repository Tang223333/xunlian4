package com.lenovo.manufacture.hyx.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hyx.fragment.Fragment1;
import com.lenovo.manufacture.hyx.fragment.Fragment2;
import com.lenovo.manufacture.hyx.fragment.Fragment3;

import java.util.ArrayList;
import java.util.List;

public class ViewPageActivity extends AppCompatActivity {

    private ViewPager fragmentLayout;
    private List<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        initView();
    }

    private void initView() {
        fragments = new ArrayList<>();
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());

        fragmentLayout = (ViewPager) findViewById(R.id.fragmentLayout);
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager(),1) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };

        fragmentLayout.setAdapter(fragmentPagerAdapter);

        setchange();
    }

    private void setchange() {
        fragmentLayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
//                        TimerTask.makeText(ViewPageActivity.this, position, TimerTask.LENGTH_SHORT).show();
                        break;
                    case 1:
//                        TimerTask.makeText(ViewPageActivity.this, position, TimerTask.LENGTH_SHORT).show();
                        break;
                    case 2:
//                        TimerTask.makeText(ViewPageActivity.this, position, TimerTask.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
