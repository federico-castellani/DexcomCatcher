package com.dexcomcatcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1;
    private String[] permissions = { "com.dexcom.cgm.EXTERNAL_PERMISSION" };

    static TextView glicemiaT;
    static TextView frecciaT;
    static TextView timestampT;
    static TextView timestamp2T;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

        glicemiaT = findViewById(R.id.glicemia);
        frecciaT = findViewById(R.id.freccia);
        timestampT = findViewById(R.id.timestamp);
        timestamp2T = findViewById(R.id.timestamp2);


        if (ContextCompat.checkSelfPermission(this, "com.dexcom.cgm.EXTERNAL_PERMISSION")
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
        } else {
            //permesso accettato
            Intent serviceIntent = new Intent(this, DexcomService.class);
            startService(serviceIntent);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permesso accettato
                Intent serviceIntent = new Intent(this, DexcomService.class);
                startService(serviceIntent);
            } else {
                //permesso rifiutato
            }
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the notification channel
            String channelId = "DexcomServiceChannel";
            CharSequence channelName = "Dexcom Catcher";
            String channelDescription = "Glicemia";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setDescription(channelDescription);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}