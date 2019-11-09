package com.lenovo.manufacture.dominator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.lenovo.manufacture.Main6Activity;
import com.lenovo.manufacture.R;

public class D_Main_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dominator_activity_jump1);

    }

    public void text_onclick(View view) {
        startActivity(new Intent(this, Main6Activity.class));
    }
}
