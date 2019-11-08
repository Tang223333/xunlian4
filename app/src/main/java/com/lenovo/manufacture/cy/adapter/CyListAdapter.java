package com.lenovo.manufacture.cy.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.lenovo.manufacture.R;
import com.lenovo.manufacture.cy.activity.cyActivity;

public class CyListAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Demo> objects = new ArrayList<Demo>();
    private Context context;
    private LayoutInflater layoutInflater;
    private String str;
    private ViewHolder holder;

    public CyListAdapter(Context context, List<Demo> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects = objects;
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
        initializeViews((Object) getItem(position), (ViewHolder) convertView.getTag());

        SharedPreferences sharedPreferences=context.getSharedPreferences("filerw",0);
        str=sharedPreferences.getString("w","0");

        holder = new ViewHolder(convertView);
        holder.cyListTv1.setOnClickListener(this);
        holder.cyListTv2.setOnClickListener(this);
        holder.cyListTv3.setOnClickListener(this);
        holder.cyListTv4.setOnClickListener(this);
        holder.cyListBtn.setOnClickListener(this);
        holder.cyListTv1.setText(""+objects.get(position).getA());
        holder.cyListTv2.setText(""+objects.get(position).getB());
        holder.cyListTv3.setText(str);
        holder.cyListTv4.setText(str+"0.0");
        return convertView;
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {

        SharedPreferences sharedPreferences=context.getSharedPreferences("filerw",0);

        switch (view.getId()){
            case R.id.cy_list_tv1:
                AlertDialog.Builder dialog=new AlertDialog.Builder(context)
                        .setTitle("title")
                        .setMessage("message")
                        .setNegativeButton("Button", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "toast", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.create();
                dialog.show();
                break;
            case R.id.cy_list_tv2:
                Intent intent=new Intent(context, cyActivity.class);
                intent.putExtra("key","value");
                PendingIntent pend=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.createNotificationChannel(new NotificationChannel("123","asdf",4));//4个等级 4最高，1最低
                NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"123")
                        .setContentTitle("title")
                        .setContentText("text")
                        .setContentIntent(pend)
                        .setSmallIcon(R.mipmap.ic_launcher);
                manager.notify(1,builder.build());
                break;
            case R.id.cy_list_tv3:
                sharedPreferences.edit().putString("w",str+"1").apply();//写入filerw文件的w键值对
                str=sharedPreferences.getString("w","0");
                break;
            case R.id.cy_list_tv4:
                str = sharedPreferences.getString("w","0");//读取filerw文件的w键值对，空返回"0"
                holder.cyListTv3.setText(str);
                break;
            case R.id.cy_list_btn:
                context.startActivity(new Intent(context,cyActivity.class));
                break;
        }
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
