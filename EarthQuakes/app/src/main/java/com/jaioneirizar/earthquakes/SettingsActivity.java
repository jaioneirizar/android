package com.jaioneirizar.earthquakes;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;


import com.jaioneirizar.earthquakes.fragments.SettingsFragment;
import com.jaioneirizar.earthquakes.services.DownloadEarthQuakesService;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  addPreferencesFromResource(R.xml.userpreferences);
        getFragmentManager()
          .beginTransaction()
            .replace(android.R.id.content, new SettingsFragment())
            .commit();

       SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
       pref.registerOnSharedPreferenceChangeListener(this);


    }



   // @Override
    public void onSharedPreferenceChanged(SharedPreferences pref, String key) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(this.ALARM_SERVICE);
        int alarmtype = AlarmManager.RTC;
        long timerOrLengthofWait = AlarmManager.INTERVAL_HOUR;
        String ALARM_ACTION = "ALARM_ACTION";
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent alarmIntent = PendingIntent.getService(this, 0, intent, 0);


        if (key.equals(getString(R.string.pref_auto_update))) {
            if(pref.getBoolean(getString(R.string.pref_auto_update), false)){
            alarmManager.setRepeating(alarmtype, 0, timerOrLengthofWait, alarmIntent);


         } else {
            alarmManager.cancel(alarmIntent);
            }
         }
            else if(key.equals(getString(R.string.pref_auto_interval))){

            alarmManager.setRepeating(alarmtype,0,timerOrLengthofWait, alarmIntent);


        }

    }

}



