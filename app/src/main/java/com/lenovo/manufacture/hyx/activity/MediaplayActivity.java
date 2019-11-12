package com.lenovo.manufacture.hyx.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;

import com.lenovo.manufacture.R;

import java.util.Timer;
import java.util.TimerTask;

public class MediaplayActivity extends AppCompatActivity {
    private static final String TAG = "MediaplayActivity";

    private TextView gti;
    private AppCompatSeekBar bar;
    private TextView wti;
    private ImageView play;
    private Boolean palys=false;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private int t=0;
    int j=t;
    private int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplay);
        initView();

    }

    private void initView() {
        gti = (TextView) findViewById(R.id.gti);
        bar = (AppCompatSeekBar) findViewById(R.id.bar);
        wti = (TextView) findViewById(R.id.wti);
        play = (ImageView) findViewById(R.id.play);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mediaPlayer==null){
//                    mediaPlayer = MediaPlayer.create(MediaplayActivity.this,R.raw.ge);
                }
                int duration = mediaPlayer.getDuration();
                int only = duration/100;
                currentPosition = mediaPlayer.getCurrentPosition();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        gti.setText(gettime(currentPosition)+"");
                        wti.setText(gettime(duration- currentPosition)+"");
                        if (currentPosition ==duration){
                            mediaPlayer.stop();
                        }



                        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                                Log.d(TAG, "onProgressChanged: "+i);

                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {
                            }

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {
                                int progress = seekBar.getProgress();
                                t= progress;
                                Log.d(TAG, "onStopTrackingTouch: "+progress);

                            }
                        });

                        if (j!=t){
                            j=t;
                            currentPosition =(t*only)/1000;
                            mediaPlayer.seekTo(currentPosition*1000);
                        }
                        bar.setProgress(currentPosition/only);
                        play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (palys){
                                    Bitmap bitmap  = BitmapFactory.decodeResource(getResources(),R.mipmap.paly);
                                    play.setImageBitmap(bitmap);
                                    palys=false;
                                    mediaPlayer.pause();
                                }else {
                                    Bitmap bitmap  = BitmapFactory.decodeResource(getResources(),R.mipmap.stop);
                                    play.setImageBitmap(bitmap);
                                    palys=true;
                                    mediaPlayer.start();
                                }
                            }
                        });
                    }
                });
            }
        },0,100);

    }
    public String gettime(int duration){
        int m = duration/1000;
        int s = m/60;
        return s+":"+(m-s*60)+"";
    }
}
