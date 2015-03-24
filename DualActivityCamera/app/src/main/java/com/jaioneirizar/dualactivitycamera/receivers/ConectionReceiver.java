package com.jaioneirizar.dualactivitycamera.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

import java.net.ConnectException;

public class ConectionReceiver extends BroadcastReceiver {

    private static final String RECEIVER = "RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {

            Log.d(RECEIVER, "ConectionReceiver onReceiver()");
        Log.d(RECEIVER, "ACTION:" + intent.getAction());

        if(intent.getAction().equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {

            //Do somthing

        }else if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)){

        }


    }
}
