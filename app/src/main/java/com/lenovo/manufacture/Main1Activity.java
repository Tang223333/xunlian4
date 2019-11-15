package com.lenovo.manufacture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.hyx.activity.DownActivityh;
import com.lenovo.manufacture.hyx.activity.MediaplayActivity;
import com.lenovo.manufacture.hyx.activity.ServiceActivity;
import com.lenovo.manufacture.hyx.activity.ViewPageActivity;

public class Main1Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView text;
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initView();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main1Activity.this, ViewPageActivity.class));
            }
        });
        mText1 = (TextView) findViewById(R.id.text1);
        mText1.setOnClickListener(this);
        mText2 = (TextView) findViewById(R.id.text2);
        mText2.setOnClickListener(this);
        mText3 = (TextView) findViewById(R.id.text3);
        mText3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text1:
                startActivity(new Intent(Main1Activity.this, DownActivityh.class));
                break;
            case R.id.text2:
                startActivity(new Intent(Main1Activity.this, MediaplayActivity.class));
                break;
            case R.id.text3:
                startActivity(new Intent(Main1Activity.this, ServiceActivity.class));
                break;
        }
    }
}
