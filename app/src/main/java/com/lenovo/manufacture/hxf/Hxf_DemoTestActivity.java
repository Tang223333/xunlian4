package com.lenovo.manufacture.hxf;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hxf.Utils.MyOkHttp;

import org.json.JSONObject;

public class Hxf_DemoTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Hxf_DemoTestActivity";
    private TextView textView;
    private LinearLayout.LayoutParams layoutParams;
    private Button button;
    Handler handler = new Handler();
    private JSONObject resultData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hxf__demo_test);
        layoutParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addViewToLayout();
        addContentView(button,layoutParams);
        addContentView(textView, layoutParams);

    }

    private void addViewToLayout() {
        //TODO 在布局中动态添加一个TextView
        textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        textView.setText("这是我动态添加的一个TextView");
        textView.setTextColor(Color.BLUE);

        button = new Button(this);
        button.setText("请点击我以获取网络数据~");
        button.setId(0x001);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case 0x001:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject jsonObject = new JSONObject();
                            jsonObject.put("UserName","user1");
                            JSONObject jsonObject1 = MyOkHttp.postData(getApplicationContext(), "GetAllSense.do", jsonObject.toString());
                            if (jsonObject1!=null){
                                resultData = jsonObject1;
                            }else {
                                Toast.makeText(Hxf_DemoTestActivity.this, "未获取到任何数据！", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d(TAG, "run: ========" + e);
                        }
                    }
                }).start();
                textView.setText(resultData.toString()+"");
                break;



        }
    }
}
