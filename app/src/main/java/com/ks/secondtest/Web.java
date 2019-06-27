package com.ks.secondtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web extends AppCompatActivity {

    private WebView mWb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        mWb = (WebView) findViewById(R.id.wb);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        mWb.loadUrl(url);
        mWb.setWebViewClient(new WebViewClient());

    }
}
