package com.jaioneirizar.earthquakes;


import android.os.Bundle;
import android.preference.PreferenceActivity;


import com.jaioneirizar.earthquakes.fragments.SettingsFragment;

public class SettingsActivity extends PreferenceActivity  {

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
   /* public void onSharedPreferenceChanged(SharedPreferences pref, String key) {

        if(pref.getString("jaione","").equals("PREF_AUTO_UPDATE")){

            Log.d("jaione", "jaione");


        }*/




}
