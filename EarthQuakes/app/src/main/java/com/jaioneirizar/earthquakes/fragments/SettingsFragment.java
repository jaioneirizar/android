package com.jaioneirizar.earthquakes.fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import com.jaioneirizar.earthquakes.R;

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.userpreferences);

      SharedPreferences  prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity().getApplicationContext());
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        //String PREF_AUTO_UPDATE = getString(R.string.)
        //String


        /*if (key.equals("PREF_AUTO_UPDATE")) {


        } else if ((key.equals("UPDATE_INTERVAL"))){

        } else if ((key.equals("Magnitude"))) {


            double minMag = Double.parseDouble(sharedPreferences.getString(key, ""));


        }*/


    }
}


