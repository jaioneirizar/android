package com.jaioneirizar.earthquakes.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaioneirizar.earthquakes.R;
import com.jaioneirizar.earthquakes.abstracts.AbstractMapFragment;
import com.jaioneirizar.earthquakes.model.EarthQuake;


/**
 * Created by cursomovil on 13/04/15.
 */
public class EarthQuakesMapsListFragment extends AbstractMapFragment {

    private SharedPreferences prefs;
    @Override
    protected void obtenerData() {
        int minMag = Integer.parseInt(prefs.getString(getString(R.string.magnitude), "0"));
        earthQuakes=db.getAllByMagnitude(minMag);
    }

    @Override
    protected void PintarMapa() {

        mapa.clear();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (EarthQuake earthQuake: earthQuakes) {

            MarkerOptions marker = crearMarker(earthQuake);

            mapa.addMarker(marker);
            builder.include(marker.getPosition());
        }

        LatLngBounds bounds = builder.build();

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 10);

       mapa.animateCamera(cu);
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());
    }


    @Override
    public void onResume() {
        super.onResume();

        getMap().setOnMapLoadedCallback(this);
    }
}