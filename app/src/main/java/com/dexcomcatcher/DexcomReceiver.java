package com.dexcomcatcher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class DexcomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equals("com.dexcom.cgm.EXTERNAL_BROADCAST")) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                Bundle bgs = extras.getBundle("glucoseValues"); //tutte le scansioni

                for(int i = 1; i <= 288; i++) {
                    Bundle valori = bgs.getBundle(String.valueOf(i-1));
                    Log.d("valori", i + " "+ valori.getInt("glucoseValue"));
                }

                Bundle glucoseValueBundle = bgs.getBundle(String.valueOf(bgs.size() - 1)); //ultima scansione

                if(glucoseValueBundle != null) {
                    long maxTimestamp = 0L;
                    int glucoseValue = 0;
                    String freccia = null;

                    int value = glucoseValueBundle.getInt("glucoseValue");
                    long timestamp = glucoseValueBundle.getLong("timestamp");

                    if (timestamp > maxTimestamp && value > 0) {
                        maxTimestamp = timestamp;
                        glucoseValue = value;
                        freccia = glucoseValueBundle.getString("trendArrow");
                    }
                    if (maxTimestamp != 0 && glucoseValue > 0) {
                        onDataReceived(glucoseValue, maxTimestamp, freccia);
                    }
                }


            }
        }
    }
    private void onDataReceived(int glucoseValue, long timestamp, String freccia) {
        MainActivity.glicemiaT.setText("Glicemia: " + glucoseValue + " mg/dl");
        MainActivity.frecciaT.setText(freccia);
        MainActivity.timestampT.setText("" + timestamp);

    }
}
