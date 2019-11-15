package com.lenovo.manufacture.hxf.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {
    List<View> viewList; //viewPager界面数据绑定
    List<String> stringList;//viewPager界面上面标题绑定数据

    public MyViewPagerAdapter(List<View> viewList, List<String> stringList) {
        this.viewList = viewList;
        this.stringList = stringList;
    }

    //TODO 得到viewPager(list集合的大小)的个数
    @Override
    public int getCount() {
        return viewList.size();
    }

    //TODO 判断是来自于哪个视图(viewPager)
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    //TODO 通过position把视图(viewPager)加载进来
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(viewList.get(position));
        return viewList.get(position);
    }

    //TODO 通过position销毁视图
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(viewList.get(position));
    }

    //TODO 通过viewPager的id与标题(PagerTabStrip)绑定
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }

}
