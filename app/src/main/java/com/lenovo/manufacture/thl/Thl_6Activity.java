package com.lenovo.manufacture.thl;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
import com.lenovo.manufacture.R;

public class Thl_6Activity extends AppCompatActivity {

    private AppBarLayout thl6Abl;
    private Toolbar thl6Tb;
    private NestedScrollView thl6Nsv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thl_6);
        initView();
    }

    private void initView() {
        thl6Abl = (AppBarLayout) findViewById(R.id.thl6_abl);
        thl6Tb = (Toolbar) findViewById(R.id.thl6_tb);
        thl6Nsv = (NestedScrollView) findViewById(R.id.thl6_nsv);

        setSupportActionBar(thl6Tb);
    }
}
