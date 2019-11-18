package com.lenovo.manufacture.thl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.lenovo.manufacture.R;

public class Thl_4Activity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "Thl_4Activity========";
    private CoordinatorLayout thl4Cl;
    private LinearLayout thl4Ll;
    private Button thl4Btn1;
    private Button thl4Btn2;
    private FloatingActionButton thl4FaBtn;
    private boolean isshow=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thl_4);
        initView();
    }

    private void initView() {
        thl4Cl = (CoordinatorLayout) findViewById(R.id.thl4_cl);
        thl4Ll = (LinearLayout) findViewById(R.id.thl4_ll);
        thl4Btn1 = (Button) findViewById(R.id.thl4_btn1);
        thl4Btn2 = (Button) findViewById(R.id.thl4_btn2);
        thl4FaBtn = (FloatingActionButton) findViewById(R.id.thl4_fa_btn);

        thl4Btn1.setOnClickListener(this);
        thl4Btn2.setOnClickListener(this);
        
        thl4FaBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.thl4_btn1:
                Snackbar snackbar=Snackbar.make(v,"简单提示框",Snackbar.LENGTH_SHORT);
                snackbar.show();
                break;
            case R.id.thl4_btn2:
                if (isshow){
                    thl4Btn2.setText("显示悬浮按钮");
                    thl4FaBtn.hide();
                    isshow=false;
                }else {
                    thl4Btn2.setText("隐藏悬浮按钮");
                    thl4FaBtn.show();
                    isshow=true;
                }
                break;
            case R.id.thl4_fa_btn:
                Toast.makeText(this, "你点了个球", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
