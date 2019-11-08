package com.lenovo.manufacture.thl.frament;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.OkHttp.OkHttp;
import com.lenovo.manufacture.thl.adapter.ListItemAdapter;
import com.lenovo.manufacture.thl.data.Car_Yue;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ThlFragment01Fragment extends Fragment {

    private TextView text01;
    private TextView text02;
    private TextView text03;
    private TextView text04;
    private ListView list01;
    private Context context;
    private List<Car_Yue> list;
    OkHttp okHttp;

    Handler handler=new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.thl_fragment_01, null);
        Log.d("ThlFragment01Fragment", "1");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("ThlFragment01Fragment", "2:" + 2);
        text01 = (TextView) view.findViewById(R.id.text_01);
        text02 = (TextView) view.findViewById(R.id.text_02);
        text03 = (TextView) view.findViewById(R.id.text_03);
        text03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        text04 = (TextView) view.findViewById(R.id.text_04);
        list01 = (ListView) view.findViewById(R.id.list_01);
        context=getContext();
        list=new ArrayList<>();
        list.clear();
        new Thread(){
            @Override
            public void run() {
                super.run();
                post();
            }
        }.start();
    }

    private void post() {
        okHttp=new OkHttp();
        for (int i = 1; i <5 ; i++) {
            try {
                String url="GetCarAccountBalance.do";
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("CarId",i);
                jsonObject.put("UserName","user1");
                Log.d("ThlFragment01Fragment", jsonObject.toString());
                jsonObject=okHttp.POST(context,url,jsonObject.toString());
                Log.d("ThlFragment01Fragment", jsonObject.toString());
                list.add(new Car_Yue(i,jsonObject.getInt("Balance")));
            } catch (Exception e) {
                e.printStackTrace();
                list.add(new Car_Yue(i,100));
            }
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                ListItemAdapter listItemAdapter=new ListItemAdapter(context,list);
                list01.setAdapter(listItemAdapter);
            }
        });
    }


}