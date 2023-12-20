package com.dexcomcatcher;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;

public class DexcomService extends Service {

    private DexcomReceiver dexcomReceiver;
    private static final String CHANNEL_ID = "DexcomServiceChannel";

    @Override
    public void onCreate() {
        super.onCreate();
        dexcomReceiver = new DexcomReceiver();
        IntentFilter intentFilter = new IntentFilter("com.dexcom.cgm.EXTERNAL_BROADCAST");
        registerReceiver(dexcomReceiver, intentFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createNotificationChannel();
        Notification notification = new Notification.Builder(this, CHANNEL_ID)
                .setContentTitle("Dexcom Catcher")
                .setContentText("Running in the background")
                .setSmallIcon(R.drawable.notification_icon)
                .setOngoing(true)
                .build();
        startForeground(1, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(dexcomReceiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Dexcom Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
