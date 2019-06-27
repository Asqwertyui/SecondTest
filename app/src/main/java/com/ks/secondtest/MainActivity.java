package com.ks.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
//苏清芬
public class MainActivity extends AppCompatActivity {


    private ImageView mIv;
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mIv = (ImageView) findViewById(R.id.iv);
        mTv = (TextView) findViewById(R.id.tv);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(new TranslateAnimation(0,50,50,0));
        set.addAnimation(new ScaleAnimation(10,0,0,10));
        set.addAnimation(new RotateAnimation(90,360));
        set.addAnimation(new AlphaAnimation(1,0));
        set.setDuration(3000);
        mIv.startAnimation(set);
        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTv.setText("倒计时"+millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
            startActivity(new Intent(MainActivity.this,Vp.class));
            }
        }.start();
    }
}
