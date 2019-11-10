package com.lenovo.manufacture.hxf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import com.google.gson.Gson;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hxf.Bean.Person;
import com.lenovo.manufacture.hxf.Utils.MyOkHttp;
import com.lenovo.manufacture.hxf.adapter.MyAdapter;

import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Hxf_DemoTestActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Hxf_DemoTestActivity";
    private static boolean STATE_REFRESH = true;
    private TextView textView;
    private LinearLayout.LayoutParams layoutParams;
    private Button button;
    private Button button1;
    Handler handler = new Handler();
    private JSONObject resultData;
    private Timer timer;
    private LinearLayout layout_father;
    public static int Height = 100;
    public static int Width = 100;
    private RecyclerView rc_view;
    private Person person = new Person();
    private List<Person> mData = new LinkedList<>();
    private MyAdapter myAdapter;
    private LinearLayout linearLayout;
    private Button btn_scrollStart;
    private Button btn_ScrollStop;
    private Button btn_scrollEnd;
    private Button btn_addAll;
    private Button btn_DeleteFirst;
    private Button btn_clearDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hxf__demo_test);
        startTimer();
        layoutParams = new LinearLayout
                .LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        addViewToLayout();
        layout_father.addView(textView);
        linearLayout.addView(button);
        linearLayout.addView(button1);
        layout_father.addView(linearLayout);
    }

    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
//                    Thread.sleep(5000);
//                    textView.setVisibility(View.INVISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                postData();
            }
        }, 0, 100);
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

    private void addViewToLayout() {
        layout_father = findViewById(R.id.layout_father);
        rc_view = findViewById(R.id.rc_View);
        //TODO 必选属性
//        rc_view.setLayoutManager(new GridLayoutManager(getApplicationContext(), 7, GridLayoutManager.VERTICAL, false));
        rc_view.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        //TODO 可选属性
        rc_view.setItemAnimator(new DefaultItemAnimator());

        myAdapter = new MyAdapter(this, mData);
        rc_view.setAdapter(myAdapter);
        //TODO 在布局中动态添加一个TextView
        textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setLayoutParams(layoutParams);
        textView.setText("这是我动态添加的一个TextView");
        textView.setTextColor(Color.BLUE);
        textView.setTextSize(38);
        textView.setSelected(true);
        textView.setBackgroundColor(Color.alpha(0));
        textView.onCheckIsTextEditor();
        textView.setSelectAllOnFocus(true);
        textView.setPadding(50, 0, 50, 80);

        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setLayoutParams(layoutParams);

        button = new Button(this);
        int btn_id = 0x001;
        button.setText("请点击我以获取网络数据~");
        button.setId(btn_id);
        button.setLayoutParams(layoutParams);
        button.setOnClickListener(this);

        button1 = new Button(this);
        int btn_id1 = 0x002;
        button1.setText("停止获取网络数据");
        button1.setId(btn_id1);
        button1.setLayoutParams(layoutParams);
        button1.setOnClickListener(this);

        btn_clearDisplay = findViewById(R.id.btn_ClearDisplay);
        btn_clearDisplay.setOnClickListener(this);
        btn_DeleteFirst = findViewById(R.id.btn_DeleteFirst);
        btn_DeleteFirst.setOnClickListener(this);

        btn_scrollStart = findViewById(R.id.btn_ScrollStart);
        btn_scrollStart.setOnClickListener(this);
        btn_ScrollStop = findViewById(R.id.btn_ScrollStop);
        btn_ScrollStop.setOnClickListener(this);
        btn_scrollEnd = findViewById(R.id.btn_ScrollEnd);
        btn_scrollEnd.setOnClickListener(this);

        btn_addAll = findViewById(R.id.btn_addAll);
        btn_addAll.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case 0x001:
                stopTimer();
                startTimer();
                STATE_REFRESH = true;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postData();
                    }
                });
                //TODO 判断该线程是否活着
                if (!thread.isAlive()) {
                    thread.start();
                }
                break;
            case 0x002:
                stopTimer();
                STATE_REFRESH = false;
                break;
            case R.id.btn_ClearDisplay:
                if (!mData.isEmpty()){
                    for (int i = mData.size()-1; i > -1; i--) {
                        mData.remove(i);
                        myAdapter.notifyItemRemoved(i);
                    }
                }
                myAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_DeleteFirst:
                if (!mData.isEmpty()) {
                    mData.remove(0);
                    myAdapter.notifyItemRemoved(0);
                }
//                myAdapter.notifyDataSetChanged();
                break;
            case R.id.btn_ScrollStart:
                selectStart();
                break;
            case R.id.btn_ScrollStop:
                stopScroll();
                break;
            case R.id.btn_ScrollEnd:
                selectEnd();
                break;
            case R.id.btn_addAll:
                Log.d(TAG, "onClick: ==============" + myAdapter.getCheckedItems());
                break;
        }
    }

    private void postData() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("UserName", "user1");
            resultData = MyOkHttp.postData(getApplicationContext(), "GetAllSense.do", jsonObject.toString());
            person = new Gson().fromJson(resultData.toString(), Person.class);

            Log.d(TAG, "postData: ============" + resultData.toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (STATE_REFRESH) {
                        if (resultData != null) {
                            textView.setVisibility(View.VISIBLE);
                            textView.setText(resultData.toString());
                            mData.add(Hxf_DemoTestActivity.this.person);
                            myAdapter.notifyDataSetChanged();

                            Log.d(TAG, "run: =========" + myAdapter.getItemCount());
                        } else {
                            Toast.makeText(getApplicationContext(), "未从服务器获取到数据！", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        selectEnd();
                        textView.setText("您已手动停止网络请求！\n(っ °Д °;)っ");
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //            textView.setVisibility(View.VISIBLE);
                    textView.setText("网络不见了！\n(っ °Д °;)っ");
                }
            });
            Log.d(TAG, "run: ========" + e);
        }
    }

    private void selectStart() {
        rc_view.smoothScrollToPosition(0);
    }

    private void stopScroll() {
        rc_view.stopScroll();
    }

    private void selectEnd() {
        //TODO 滑动到RecycleView的底部
        rc_view.smoothScrollToPosition(myAdapter.getItemCount());
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }
}
