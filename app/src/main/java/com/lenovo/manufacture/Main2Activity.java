package com.lenovo.manufacture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.thl.TangHaiLong_01;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView thl=findViewById(R.id.thl);
        thl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Main2Activity.this, TangHaiLong_01.class);
        startActivity(intent);
    }
}
