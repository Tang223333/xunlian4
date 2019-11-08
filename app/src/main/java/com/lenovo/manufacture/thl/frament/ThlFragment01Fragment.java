package com.lenovo.manufacture.thl.frament;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.MainApplication;
import com.lenovo.manufacture.thl.OkHttp.OkHttp;
import com.lenovo.manufacture.thl.adapter.ListItemAdapter;
import com.lenovo.manufacture.thl.data.Car_Yue;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ThlFragment01Fragment extends Fragment {
    private boolean[] booleans={false,false,false,false};

    private TextView text01;
    private TextView text02;
    private TextView text03;
    private TextView text04;
    private ListView list01;
    private Context context;
    private List<Car_Yue> list;
    OkHttp okHttp;
    MainApplication application;

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
        application=MainApplication.getInstance();
        text01 = (TextView) view.findViewById(R.id.text_01);
        text02 = (TextView) view.findViewById(R.id.text_02);
        text03 = (TextView) view.findViewById(R.id.text_03);
        text03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean[] b = {false,false,false,false};
                for (int i = 0; i < 4; i++) {
                    application=MainApplication.getInstance();
                    if (application.mBoolean.get(""+i)==null){
                        break;
                    }
                    b[i]=application.mBoolean.get(""+i);
                    Log.d("ThlFragment01Fragment", "application.mBoolean.get(\"\"+i):"+i + application.mBoolean.get(""+i));
                }
                String s="";
                for (int i = 0; i < 4; i++) {
                    if (b[i]==true){
                        s=s+""+(i+1)+",";
                    }
                }
                Dialog dialog=new Dialog(context);
                dialog.show();
                dialog.getWindow().setContentView(R.layout.dialog_item);
                TextView title=dialog.getWindow().findViewById(R.id.text_title);
                TextView body1=dialog.getWindow().findViewById(R.id.text_car_id);
                EditText editText_sr=dialog.getWindow().findViewById(R.id.editText_srje);
                Button btn_qx=dialog.getWindow().findViewById(R.id.btn_qx);
                Button btn_qr=dialog.getWindow().findViewById(R.id.btn_qr);
                body1.setText("小车编号:"+s);
                editText_sr.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (s.toString().matches("^0")){
                            editText_sr.setText("");
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                });
                btn_qx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_qr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editText_sr.getText().toString().equals("")){
                            Toast.makeText(context, "空", Toast.LENGTH_SHORT).show();
                        }else {
                            for (int i = 0; i < 4; i++) {
                                if (b[i]==true){
                                    int a=Integer.parseInt(editText_sr.getText().toString());
                                    int finalI = i+1;
                                    new Thread(){
                                        @Override
                                        public void run() {
                                            super.run();
                                            postcz(finalI,a);
                                        }
                                    }.start();
                                }
                            }
                            new Thread(){
                                @Override
                                public void run() {
                                    super.run();
                                    list=new ArrayList<>();
                                    list.clear();
                                    post();
                                }
                            }.start();
                            dialog.dismiss();
                        }

                    }
                });
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
                list01.setAdapter(baseAdapter);
            }
        });
    }

    private void postcz(int i,int money) {
        okHttp=new OkHttp();
        try {
            String url="SetCarAccountRecharge.do";
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("CarId",i);
            jsonObject.put("Money",money);
            jsonObject.put("UserName","user1");
            Log.d("ThlFragment01Fragment", jsonObject.toString());
            jsonObject=okHttp.POST(context,url,jsonObject.toString());
            Log.d("ThlFragment01Fragment", jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "充值失败", Toast.LENGTH_SHORT).show();
        }
    }

    BaseAdapter baseAdapter=new BaseAdapter() {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(context,R.layout.list_item,null);
            TextView textView=view.findViewById(R.id.list_text_01);
            TextView textView1=view.findViewById(R.id.list_text_02);
            CheckBox checkBox=view.findViewById(R.id.list_checkbox_01);
            Button button=view.findViewById(R.id.list_button_01);
            textView.setText(list.get(position).getCar_id()+"");
            textView1.setText(list.get(position).getCar_yue()+"");
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    booleans[position]=isChecked;
                    MainApplication application=MainApplication.getInstance();
                    for (int i = 0; i < 4; i++) {
                        application.mBoolean.put(""+i,booleans[i]);
                        Toast.makeText(application, ""+booleans[i]+application.mBoolean.get(""+i), Toast.LENGTH_SHORT).show();
                    }

                }
            });
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog=new Dialog(context);
                    dialog.show();
                    dialog.getWindow().setContentView(R.layout.dialog_item);
                    TextView text_title=dialog.getWindow().findViewById(R.id.text_title);
                    TextView text_car_id=dialog.getWindow().findViewById(R.id.text_car_id);
                    text_car_id.setText("小车编号:"+(position+1));
                    EditText editText=dialog.getWindow().findViewById(R.id.editText_srje);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }
                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (s.toString().matches("^0")){
                                editText.setText("");
                            }
                        }
                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });
                    Button button1=dialog.getWindow().findViewById(R.id.btn_qx);
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    Button button2=dialog.getWindow().findViewById(R.id.btn_qr);
                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (editText.getText().toString().equals("")){
                                Toast.makeText(context, "空空", Toast.LENGTH_SHORT).show();
                            }else {
                                new Thread(){
                                    @Override
                                    public void run() {
                                        super.run();
                                        postcz(position,(Integer.parseInt(editText.getText().toString())+1));
                                    }
                                }.start();
                                list=new ArrayList<>();
                                list.clear();
                                new Thread(){
                                    @Override
                                    public void run() {
                                        super.run();
                                        post();
                                    }
                                }.start();
                                dialog.dismiss();
                            }
                        }
                    });
                }
            });
            return view;
        }
    };


}