package com.lenovo.manufacture.cy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;

import com.lenovo.manufacture.R;

public class CyListAdapter extends BaseAdapter {

    private List<Object> objects = new ArrayList<Object>();

    private Context context;
    private LayoutInflater layoutInflater;


    public CyListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cy_list, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((Object)getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Object object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private TextView cyListTv1;
    private TextView cyListTv2;
    private TextView cyListTv3;
    private TextView cyListTv4;
    private Button cyListBtn;

        public ViewHolder(View view) {
            cyListTv1 = (TextView) view.findViewById(R.id.cy_list_tv1);
            cyListTv2 = (TextView) view.findViewById(R.id.cy_list_tv2);
            cyListTv3 = (TextView) view.findViewById(R.id.cy_list_tv3);
            cyListTv4 = (TextView) view.findViewById(R.id.cy_list_tv4);
            cyListBtn = (Button) view.findViewById(R.id.cy_list_btn);
        }
    }
}
