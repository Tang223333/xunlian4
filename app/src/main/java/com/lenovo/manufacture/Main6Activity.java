package com.lenovo.manufacture;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.dominator.D_Main_Activity;

public class Main6Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

    }

    public void tv_jump(View view) {
        Intent intent = new Intent(Main6Activity.this, D_Main_Activity.class);
        startActivity(intent);
    }
}
