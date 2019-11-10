package com.lenovo.manufacture;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.lenovo.manufacture.dominator.MessageActivity;

import java.util.Locale;

import static com.lenovo.manufacture.R.layout.dominator_view_dialog;

public class Main6Activity extends AppCompatActivity {
    /**
     *
     * 每增加一个demo增加一个
     *  if(wherego.getText().toString().equals("message"))
     *
     *
     *
     */



    AlertDialog alert=null;
    AlertDialog.Builder builder = null;
    View mydialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

    }


    public void btn_selectdialog(View view) {

        builder = new AlertDialog.Builder(Main6Activity.this);

        //加载自定义的那个View,同时设置下
        final LayoutInflater inflater = Main6Activity.this.getLayoutInflater();
      mydialog = inflater.inflate(dominator_view_dialog, null,false);
        builder.setView(mydialog);
        builder.setCancelable(false);
        alert = builder.create();

        alert.show();

        EditText wherego=mydialog.findViewById(R.id.dominator_place);

        mydialog.findViewById(R.id.dominator_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              if(wherego.getText().toString().equals("message")){
                  Intent intent=new Intent(Main6Activity.this,MessageActivity.class);
                  startActivity(intent);
              }




            }
        });

        mydialog.findViewById(R.id.dominator_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              alert.dismiss();




            }
        });








    }
}
