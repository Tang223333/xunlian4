package com.lenovo.manufacture.hyx.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hyx.service.Service1;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBnt1;
    private Button mBnt2;
    private Intent mintent;
    private Service1 service1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        initView();
//        startService(new Intent(this, Service1.class));
    }

    private void initView() {
        mBnt1 = (Button) findViewById(R.id.bnt1);
        mBnt2 = (Button) findViewById(R.id.bnt2);

        mBnt1.setOnClickListener(this);
        mBnt2.setOnClickListener(this);
        mintent = new Intent(this,Service1.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnt1:
                boolean b = bindService(mintent, mFirstConn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.bnt2:
                if (service1!=null){
                    unbindService(mFirstConn);
                    service1=null;
                }
                break;
        }
    }
    private ServiceConnection mFirstConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service1 = ((Service1.LocalBinder)iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            service1 = null;
        }
    };
}
