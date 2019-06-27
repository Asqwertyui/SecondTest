package com.ks.secondtest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by F0519 on 2019/6/26.
 */

public class Thread implements Runnable{

    private File mSd;
private Context context;
boolean isrunning=true;
    public Thread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mSd = Environment.getExternalStorageDirectory();

        }
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url("http://cdn.banmi.com/banmiapp/apk/banmi_330.apk")
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("tga",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();
                long max = body.contentLength();

                saveFial(inputStream,max,mSd+"aa.apk");
            }
        });
    }
private long count;

    private void saveFial(InputStream inputStream, long max, String s) {
        try {
            FileOutputStream outputStream = new FileOutputStream(new File(s));
            byte[] b=new byte[1024*10];
            int len=-1;
            int count=0;
            try {
                while((len=inputStream.read(b))!=-1){
                outputStream.write(b,0,len);
                        count+=len;
                    int c = (int) ((count*100) / max);
                    Intent intent = new Intent("s");
                    intent.putExtra("ss",c);
                    context.sendBroadcast(intent);
                }
                inputStream.close();
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stopThread() {
        isrunning=false;
    }
}
