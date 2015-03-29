package com.jaioneirizar.earthquakes;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;


import com.jaioneirizar.earthquakes.fragments.SettingsFragment;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  addPreferencesFromResource(R.xml.userpreferences);
        getFragmentManager()
          .beginTransaction()
            .replace(android.R.id.content, new SettingsFragment())
            .commit();

       // SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
       // pref.registerOnSharedPreferenceChangeListener(this);


    }



   // @Override
    public void onSharedPreferenceChanged(SharedPreferences pref, String key) {

        Log.d("CHANGE", key);

    }




}
