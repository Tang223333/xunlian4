package com.lenovo.manufacture.hxf;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hxf.Utils.MyOkHttp;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class Hxf_DemoTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Hxf_DemoTestActivity";
    private TextView textView;
    private LinearLayout.LayoutParams layoutParams;
    private Button button;
    Handler handler = new Handler();
    private JSONObject resultData;
    private Timer timer;
    private LinearLayout layout_father;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hxf__demo_test);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    textView.setVisibility(View.INVISIBLE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                postData();
            }
        },0,100);
        layoutParams = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        addViewToLayout();
        layout_father.addView(textView);
        layout_father.addView(button);

    }

    private void addViewToLayout() {
        layout_father = findViewById(R.id.layout_father);

        //TODO 在布局中动态添加一个TextView
        textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        textView.setText("这是我动态添加的一个TextView");
        textView.setTextColor(Color.BLUE);
        button = new Button(this);
        button.setText("请点击我以获取网络数据~");
        button.setId(0x001);
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case 0x001:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postData();
                    }
                }).start();
                break;
        }
    }

    private void postData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName","user1");
            resultData = MyOkHttp.postData(getApplicationContext(), "GetAllSense.do", jsonObject.toString());
            Log.d(TAG, "postData: ============" + resultData.toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (resultData!=null){
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(resultData.toString());
                    }else {
                        Toast.makeText(getApplicationContext(), "未从服务器获取到数据！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG, "run: ========" + e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
}
