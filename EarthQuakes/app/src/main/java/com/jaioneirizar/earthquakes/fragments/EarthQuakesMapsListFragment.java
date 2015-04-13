package com.jaioneirizar.earthquakes.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaioneirizar.earthquakes.R;
import com.jaioneirizar.earthquakes.abstracts.AbstractMapFragment;

import static java.lang.Integer.parseInt;

/**
 * Created by cursomovil on 13/04/15.
 */
public class EarthQuakesMapsListFragment extends AbstractMapFragment {

    private SharedPreferences prefs;
    @Override
    protected void obtenerData() {
       // int minMagnitude= parseInt(prefs.getString(R.string.min_magnitude);
        int minMagnitude=3;
        earthQuakes=db.getAllByMagnitude(minMagnitude);
    }

    @Override
    protected void PintarMapa() {
        LatLngBounds.Builder builder = new LatLngBounds.Builder();



        for (int i = 0; i < earthQuakes.size(); i++) {

            LatLng eartqueakeposition = new LatLng(earthQuakes.get(i).getCoords().getLng(),
                    earthQuakes.get(i).getCoords().getLat());
            String Place = earthQuakes.get(i).getPlace();
            String Url = earthQuakes.get(i).getUrl();
            Double Magnitude = earthQuakes.get(i).getMagnitude();

            getMap().setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            MarkerOptions marker = new MarkerOptions().position(eartqueakeposition).title(Place).snippet(String.valueOf(Magnitude));


            getMap().addMarker(marker);
            builder.include(marker.getPosition());


        }

        LatLngBounds bounds = builder.build();

        // LatLng position = new LatLng(Lng, Lat);
        // CameraPosition camPos = new CameraPosition.Builder().target(position)


        if (earthQuakes.size() == 1) {
            camUpd = CameraUpdateFactory.newLatLngZoom(new LatLng(earthQuakes.get(0).getCoords().getLat(),
                    earthQuakes.get(0).getCoords().getLng()), 0);
        } else {
            camUpd = CameraUpdateFactory.newLatLngBounds(bounds, 0);
        }

        mapa.moveCamera(camUpd);

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs= PreferenceManager.getDefaultSharedPreferences(getActivity());
    }
}
