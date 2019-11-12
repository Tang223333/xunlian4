package com.lenovo.manufacture.dominator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.lenovo.manufacture.R;

public class MessageActivity extends AppCompatActivity {
ViewFlipper flipper;
    final int Flag=0x001;
    Message message;//发送爽哦消息对象


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dominator_activity_message);
        flipper=findViewById(R.id.dom_viewFlipper);
         int[] imgs=new int[]{
                 R.drawable.img1,
                 R.drawable.img2,
                 R.drawable.img3,
                 R.drawable.img4,
                 R.drawable.img5

         };

        for (int i = 0; i <imgs.length ; i++) {
            ImageView imageView=new ImageView(this);
            imageView.setImageResource(imgs[i]);
            flipper.addView(imageView);
        }

        Animation[] animation=new Animation[2];
        animation[0]= AnimationUtils.loadAnimation(this,R.anim.ysb_right_in);
        animation[1]= AnimationUtils.loadAnimation(this,R.anim.ysb_right_out);

        flipper.setInAnimation(animation[0]);
        flipper.setOutAnimation(animation[1]);

       message=Message.obtain();
       message.what=Flag;

       handler.sendMessage(message);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==Flag){
                flipper.showPrevious();
                message=handler.obtainMessage(Flag);

                handler.sendMessageDelayed(message,3000);



            }
        }
    };



}
