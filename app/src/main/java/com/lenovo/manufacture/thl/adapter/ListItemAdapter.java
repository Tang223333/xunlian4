package com.lenovo.manufacture.thl.adapter;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.MainApplication;
import com.lenovo.manufacture.thl.OkHttp.OkHttp;
import com.lenovo.manufacture.thl.data.Car_Yue;
import com.lenovo.manufacture.thl.frament.ThlFragment01Fragment;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ListItemAdapter extends BaseAdapter {
    private boolean[] booleans={false,false,false,false};

    private List<Car_Yue> objects = new ArrayList<Car_Yue>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ListItemAdapter(Context context, List<Car_Yue> list) {
        this.objects=list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Car_Yue getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        TextView textView=convertView.findViewById(R.id.list_text_01);
        TextView textView1=convertView.findViewById(R.id.list_text_02);
        CheckBox checkBox=convertView.findViewById(R.id.list_checkbox_01);
        Button button=convertView.findViewById(R.id.list_button_01);
        textView.setText(objects.get(position).getCar_id()+"");
        textView1.setText(objects.get(position).getCar_yue()+"");
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleans[position]=isChecked;
                MainApplication application=MainApplication.getInstance();
                for (int i = 0; i < 4; i++) {
                    application.mBoolean.put(""+i,booleans[i]);
                    Toast.makeText(application, ""+booleans[i]+application.mBoolean.get(""+i), Toast.LENGTH_SHORT).show();
                    booleans[i]=false;
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
                text_car_id.setText("小车编号:"+position);
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
                            ThlFragment01Fragment thlFragment01Fragment=new ThlFragment01Fragment();
                        }
                    }
                });
            }
        });
        initializeViews((Car_Yue)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Car_Yue object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private TextView text01;
        private TextView text02;
        private CheckBox checkbox01;
        private Button button01;

        public ViewHolder(View view) {
            text01 = (TextView) view.findViewById(R.id.list_text_01);
            text02 = (TextView) view.findViewById(R.id.list_text_02);
            checkbox01 = (CheckBox) view.findViewById(R.id.list_checkbox_01);
            button01 = (Button) view.findViewById(R.id.list_button_01);
        }
    }
}
