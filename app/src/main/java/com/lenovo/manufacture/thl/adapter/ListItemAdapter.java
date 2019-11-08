package com.lenovo.manufacture.thl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.thl.data.Car_Yue;

import java.util.ArrayList;
import java.util.List;

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
        textView.setText(objects.get(position).getCar_id()+"");
        textView1.setText(objects.get(position).getCar_yue()+"");
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                booleans[position]=isChecked;
                Toast.makeText(context, "booleans[position]:" + booleans[position], Toast.LENGTH_SHORT).show();
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
