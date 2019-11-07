package com.lenovo.manufacture;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.hxf.Hxf_DemoTestActivity;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        startActivity(new Intent(this, Hxf_DemoTestActivity.class));
    }
}
