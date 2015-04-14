package com.jaioneirizar.earthquakes.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaioneirizar.earthquakes.R;
import com.jaioneirizar.earthquakes.abstracts.AbstractMapFragment;
import com.jaioneirizar.earthquakes.model.EarthQuake;
import com.jaioneirizar.earthquakes.services.DownloadEarthQuakesService;


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
        setHasOptionsMenu(true);
        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());
    }


    @Override
    public void onResume() {
        super.onResume();

        getMap().setOnMapLoadedCallback(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_refresh,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        if(id==R.id.action_refresh){
            Intent download;
            download = new Intent(getActivity(), DownloadEarthQuakesService.class);
            getActivity().startService(download);
        }
        return super.onOptionsItemSelected(item);
    }
}