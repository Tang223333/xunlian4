package com.lenovo.manufacture.hyx.fragment;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.lenovo.manufacture.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Fragment3 extends Fragment {
    private static final String TAG = "Fragment3";

    private TextView mTt1;
//    private Button mBtn;
    private List<String> list ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment3, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list= new ArrayList<>();

        mTt1 = (TextView) view.findViewById(R.id.tt1);

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //网络请求
//               list.add("sss");
//                mTt1.setText("11");
//
//                Log.d(TAG, "run: "+Thread.currentThread().getName());
//            }
//        }).start();
//        TimerTask.makeText(getContext(), list.get(0).toString(), TimerTask.LENGTH_SHORT).show();

    }

}
//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
////            已经注册好TextView mTt1;
//            if (msg.what==1){
//                mTt1.setText(msg.obj.toString());
//            }
//        }
//    };

//        mBtn = (Button) view.findViewById(R.id.btn1);
//        Log.d(TAG, "run: "+Thread.currentThread().getName());
//        mTt1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new Thread(){
//                    @Override
//                    public void run() {
//                        super.run();
//                        Message message = new Message();
//                        message.what = 1;
//                        message.obj = "11";
//                        handler.sendMessage(message);
//                        handler.post(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//                        mTt1.setText("11");
//                Log.d(TAG, "run: "+Thread.currentThread().getName());
//                    }
//                }.start();
//            }
//        });
////        TimerTask timer = new TimerTask();
////        timer.schedule(new TimerTask() {
////            @Override
////            public void run() {
////                mTt1.setText("11");
////                Log.d(TAG, "run: "+Thread.currentThread().getName());
////            }
////        },0,1000);