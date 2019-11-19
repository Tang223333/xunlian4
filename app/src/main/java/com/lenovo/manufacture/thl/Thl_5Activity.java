package com.lenovo.manufacture.thl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TintTypedArray;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.lenovo.manufacture.R;

public class Thl_5Activity extends AppCompatActivity {


    private AppBarLayout thl5Abl;
    private Toolbar thl5Tl;
    private RecyclerView thl5Rv;
    private String[] yearArray={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};
    private String[] yearArray2={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thl_5);
        initView();
    }

    private void initView() {
        thl5Abl = (AppBarLayout) findViewById(R.id.thl5_abl);
        thl5Tl = (Toolbar) findViewById(R.id.thl5_tl);
        thl5Rv = (RecyclerView) findViewById(R.id.thl5_rv);

        setSupportActionBar(thl5Tl);

        //创建一个垂直方向的线性布局管理器
        @SuppressLint("WrongConstant")
        LinearLayoutManager llm=new LinearLayoutManager(this, LinearLayout.VERTICAL,false);

        //设置循环视图的布局管理器
        thl5Rv.setLayoutManager(llm);
        //垢找一个适配器
        RecyclerView.Adapter<RecyclerView.ViewHolder> adapter=new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            // 创建列表项的视图持有者
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(Thl_5Activity.this).inflate(R.layout.list_item,parent,false);
                return new TitleHolder(view);
            }

            // 绑定列表项的视图持有者
            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                TitleHolder titleHolder= (TitleHolder) holder;
                titleHolder.textView.setText(yearArray[position]);
                titleHolder.textView2.setText(yearArray2[position]);
            }

            // 获取列表项的个数
            @Override
            public int getItemCount() {
                return yearArray.length;
            }

            // 获取列表项的类型
            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            // 获取列表项的编号
            @Override
            public long getItemId(int position) {
                return position;
            }

            // 定义列表项的视图持有者
            class TitleHolder extends RecyclerView.ViewHolder{
                public LinearLayout ll_item;//声明列表项的线性布局
                public TextView textView;//声明第一个TextView的文本视图
                public TextView textView2;//声明第二个

                public TitleHolder(@NonNull View itemView) {
                    super(itemView);
                    ll_item=itemView.findViewById(R.id.ll);
                    textView=itemView.findViewById(R.id.list_text_01);
                    textView2=itemView.findViewById(R.id.list_text_02);
                }
            }
        };

        thl5Rv.setAdapter(adapter);
    }
}
