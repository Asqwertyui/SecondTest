package com.ks.secondtest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {

    private Thread mThread;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mThread = new Thread(this);
        new java.lang.Thread(mThread).start();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mThread!=null){
            mThread.stopThread();
        }
    }
}
