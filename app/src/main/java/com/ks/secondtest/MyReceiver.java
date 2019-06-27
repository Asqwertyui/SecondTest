package com.ks.secondtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private NotificationManager mService;

    @Override
    public void onReceive(Context context, Intent intent) {
        String ss = intent.getStringExtra("aa");
        Toast.makeText(context, ss, Toast.LENGTH_SHORT).show();

        mService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent2 = new Intent(context, Main2Activity.class);
        Intent intent1 = intent2.putExtra("ss", ss);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 11, intent1, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new NotificationCompat.Builder(context, "1")
                .setNumber(2)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setContentTitle("颁布实施的的首付款")
                .setContentText(ss)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.lib_icon_tab4_normal)
                .build();
        mService.notify(1,notification);


    }
}
