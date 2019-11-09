package com.lenovo.manufacture.hxf.adapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.hxf.Bean.Person;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private static final String TAG = "MyAdapter";
    private Context context;
    private List<Person> mData = new LinkedList<>();
    //TODO 一个用于存储选中状态的List集合
    public Map<Integer,Integer> checkedList = new HashMap<>();
    public static int COUNT = 0;


    public Map<Integer,Integer> getCheckedItems(){
        return checkedList;
    }

    public MyAdapter() {
    }

    public MyAdapter(Context context, List<Person> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context)
                .inflate(R.layout.hxf_rc_item, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Person person = mData.get(position);
        holder.mTvId.setText(position+1+ "");
        holder.mTvPm25.setText(person.getPm25() + "");
        holder.mTvCo2.setText(person.getCo2() + "");
        holder.mTvLightIntensity.setText(person.getLightIntensity() + "");
        holder.mTvHumidity.setText(person.getHumidity() + "");
        holder.mTvTemperature.setText(person.getTemperature() + "");
        holder.mTvRESULT.setText(person.getRESULT() + "");
        holder.mTvERRMSG.setText(person.getERRMSG() + "");

        holder.mCbSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkedList.put(COUNT++,position);
                if (!isChecked) {
                    if (checkedList.containsValue(position)){
                        checkedList.remove(COUNT--);
                    }
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mTvId;
        public TextView mTvPm25;
        public TextView mTvCo2;
        public TextView mTvLightIntensity;
        public TextView mTvHumidity;
        public TextView mTvTemperature;
        public TextView mTvRESULT;
        public TextView mTvERRMSG;
        public CheckBox mCbSelect;
        public Button mBtnAdd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvId = (TextView) itemView.findViewById(R.id.tv_id);
            mTvPm25 = (TextView) itemView.findViewById(R.id.tv_pm25);
            mTvCo2 = (TextView) itemView.findViewById(R.id.tv_co2);
            mTvLightIntensity = (TextView) itemView.findViewById(R.id.tv_LightIntensity);
            mTvHumidity = (TextView) itemView.findViewById(R.id.tv_humidity);
            mTvTemperature = (TextView) itemView.findViewById(R.id.tv_temperature);
            mTvRESULT = (TextView) itemView.findViewById(R.id.tv_RESULT);
            mTvERRMSG = (TextView) itemView.findViewById(R.id.tv_ERRMSG);
            mCbSelect = (CheckBox) itemView.findViewById(R.id.cb_select);
            mBtnAdd = (Button) itemView.findViewById(R.id.btn_add);
        }
    }
}
