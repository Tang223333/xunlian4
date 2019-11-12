package com.lenovo.manufacture.hyx.fragment;


import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hyx.MyApplication;

import java.util.HashMap;


public class Fragment2 extends Fragment {

    private TextView mName;
    private TextView mAge;
    private TextView mSex;
    private TextView mAdd;
    private HashMap<String,String> hashMap = new HashMap<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment2, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mName = (TextView) view.findViewById(R.id.name);
        mAge = (TextView) view.findViewById(R.id.age);
        mSex = (TextView) view.findViewById(R.id.sex);
        mAdd = (TextView) view.findViewById(R.id.add);

        if (MyApplication.getInstance().hashMap.size()>0){
            hashMap = MyApplication.getInstance().hashMap;
            mName.setText(hashMap.get("name"));
            mAge.setText(hashMap.get("age"));
            mSex.setText(hashMap.get("sex"));
            mAdd.setText(hashMap.get("add"));
        }

    }

}
