package com.jaioneirizar.dualactivitycamera.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TelefonyReceiver extends BroadcastReceiver {


    private static final String RECEIVER ="RECEIVER" ;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(RECEIVER, "ConectionReceiver onReceiver()");
        Log.d(RECEIVER, "ACTION:" + intent.getAction());
    }
}
