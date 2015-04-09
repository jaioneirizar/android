package com.jaioneirizar.earthquakes.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaioneirizar.earthquakes.R;
import com.jaioneirizar.earthquakes.database.EarthQuakeDB;
import com.jaioneirizar.earthquakes.fragments.EarthQuakeListFragment;
import com.jaioneirizar.earthquakes.model.EarthQuake;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class EarthQuakesMapsFragment extends MapFragment implements GoogleMap.OnMapLoadedCallback {


    private EarthQuake earthQuake;
    private TextView lblid;
    private TextView lblsite;
    private TextView lblurl;
    private GoogleMap mapa =null;
    private MarkerOptions marker;
    private CameraUpdate camUpd;
    private Double Lng;
    private Double Lat;
    private List<EarthQuake> earthQuakes;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

           View layout= super.onCreateView(inflater, container, savedInstanceState);
        mapa=getMap();
        mapa.setOnMapLoadedCallback(this);
        return layout;

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setEarthQuakes(List<EarthQuake> earthquake) {

        this.earthQuakes = earthquake;

       // View layout




    }


    @Override
    public void onMapLoaded() {



        if (mapa == null) {
            // Try to obtain the map from the SupportMapFragment.
           // mapa = getMap();
            LatLngBounds.Builder builder= new LatLngBounds.Builder();

            // Check if we were successful in obtaining the map.
            if (mapa != null) {

                for (int i = 0; i < earthQuakes.size(); i++) {

                    EarthQuake earthQuake = earthQuakes.get(i);

                    Lng = earthQuake.getCoords().getLng();
                    Lat = earthQuake.getCoords().getLat();
                    String Place = earthQuake.getPlace();
                    String Url = earthQuake.getUrl();

                    mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                    MarkerOptions marker = new MarkerOptions().position(new LatLng(Lng, Lat)).title(Place).snippet(Url);

                    mapa.addMarker(marker);
                    builder.include(marker.getPosition());


                }

                LatLngBounds bounds = builder.build();

                // LatLng position = new LatLng(Lng, Lat);
                // CameraPosition camPos = new CameraPosition.Builder().target(position)

                CameraUpdate camUpd = CameraUpdateFactory.newLatLngBounds(bounds, 0);

                // .zoom(5)

                // .bearing(45)

                //   .tilt(70)

                // .build();

                //   camUpd = CameraUpdateFactory.newCameraPosition(camPos);

                //   mapa.animateCamera(camUpd);

                mapa.moveCamera(camUpd);

            }
        }

    }
}
