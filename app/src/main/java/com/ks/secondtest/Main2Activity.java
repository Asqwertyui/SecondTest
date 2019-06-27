package com.ks.secondtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ks.secondtest.bean.Myservice;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv;
    /**
     * 下载
     */
    private Button mBtload;
    private ProgressBar mPb;
    private MyReceiver2 mMyReceiver2;
    private TextView mTvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        Intent intent = getIntent();
        String ss = intent.getStringExtra("ss");
        mTv.setText(ss);
        mBtload = (Button) findViewById(R.id.btload);
        mBtload.setOnClickListener(this);
        mPb = (ProgressBar) findViewById(R.id.pb);
        mTvv = (TextView) findViewById(R.id.tvv);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btload:
                startService(new Intent(this, Myservice.class));
                break;
        }

    }

    public class MyReceiver2 extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            final int ss = intent.getIntExtra("ss", 0);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPb.setProgress(ss);
                    mTvv.setText("当前进度:" + ss + "%");
                }
            });
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyReceiver2 = new MyReceiver2();
        IntentFilter s = new IntentFilter("s");
        registerReceiver(mMyReceiver2, s);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mMyReceiver2);
    }
}
