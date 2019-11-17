package com.lenovo.manufacture.cy.adapter;

import java.util.ArrayList;
import java.util.List;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import com.lenovo.manufacture.Main4Activity;
import com.lenovo.manufacture.R;
import com.lenovo.manufacture.cy.activity.cyActivity;

public class CyListAdapter extends BaseAdapter implements View.OnClickListener {

    private List<Demo> objects = new ArrayList<Demo>();
    private Context context;
    private LayoutInflater layoutInflater;
    private String str;
    private ViewHolder holder;
    private String TAG="CyListAdapter";

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
                PendingIntent pend=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);//跳转想法

                Notification notification=new NotificationCompat.Builder(context,"123")//第二个参数要和渠道ID一样
                        .setContentTitle("title")//设置标题
                        .setContentText("text")//设置内容
                        .setContentIntent(pend)//设置跳转
                        .setSmallIcon(R.mipmap.ic_launcher)//设置小图标（必须有这个方法）
                        .setAutoCancel(true)//用户点击Notification点击面板后是否让通知取消(默认不取消)
                        .build();

                NotificationManager manager= (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                manager.createNotificationChannel(new NotificationChannel("123","123",NotificationManager.IMPORTANCE_MAX));
                //android8.0之后必须要给每个通知设置一个渠道
                //IMPORTANCE_DEFAULT：表示默认重要程度
                //IMPORTANCE_MIN：表示最低的重要程度。系统只会在用户下拉状态栏的时候才会显示
                //IMPORTANCE_LOW：表示较低的重要性，系统会将这类通知缩小，或者改变显示的顺序，将排在更重要的通知之后。
                //IMPORTANCE_HIGH：表示较高的重要程度，系统可能会将这类通知方法，或改变显示顺序，比较靠前
                //IMPORTANCE_MAX：最重要的程度， 会弹出一个单独消息框，让用户做出相应。
                manager.notify((int)(Math.random()*10000),notification);//ID给个随机值，（保证每次都能成功发送通知），也可以给个固定值
                break;
            case R.id.cy_list_tv3:

                sharedPreferences.edit().putString("w",str+"1").apply();//写入filerw文件的键值对
                str=sharedPreferences.getString("w","0");
                //进度条弹框
                ProgressDialog pdialog=new ProgressDialog(context);
                pdialog.setTitle("title");
                pdialog.setMessage("message");
                pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pdialog.setMax(100);
                pdialog.show();
                pdialog.setCancelable(false);//点击外部取消，默认为true
                new Thread(new Runnable() {//两秒后关闭
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pdialog.dismiss();
                    }
                }).start();
                break;
            case R.id.cy_list_tv4:
                str = sharedPreferences.getString("w","0");//读取filerw文件的w键值对，空返回"0"
                holder.cyListTv3.setText(str);
                Intent intent1=new Intent(Intent.ACTION_DIAL);
                Uri uri=Uri.parse("tel:"+"10086");
                intent1.setData(uri);
                context.startActivity(intent1);
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
