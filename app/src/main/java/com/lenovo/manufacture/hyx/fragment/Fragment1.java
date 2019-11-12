package com.lenovo.manufacture.hyx.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hyx.MyApplication;


/**
 * ListView 下拉刷新
 */

public class Fragment1 extends Fragment {
    private EditText mName;
    private EditText mAge;
    private EditText mSex;
    private EditText mAdd;
    private Button mTj;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTj = (Button) view.findViewById(R.id.tj);
        mName = (EditText) view.findViewById(R.id.name);
        mAge = (EditText) view.findViewById(R.id.age);
        mSex = (EditText) view.findViewById(R.id.sex);
        mAdd = (EditText) view.findViewById(R.id.add);
        mTj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mName.getText())||TextUtils.isEmpty(mAge.getText())||TextUtils.isEmpty(mSex.getText())||TextUtils.isEmpty(mAdd.getText())){
                    Toast.makeText(getContext(), "不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    MyApplication.getInstance().hashMap.put("name",mName.getText().toString());
                    MyApplication.getInstance().hashMap.put("age",mAge.getText().toString());
                    MyApplication.getInstance().hashMap.put("sex",mSex.getText().toString());
                    MyApplication.getInstance().hashMap.put("add",mAdd.getText().toString());
                }
            }
        });
    }
}
