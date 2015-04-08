package com.jaioneirizar.earthquakes;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapView extends FragmentActivity {

   private GoogleMap mapa;
    private MarkerOptions marker;
    private CameraPosition camPos;
    private CameraUpdate camUpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earth_quake_activity_detail);

       mapa= ((SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment2)).getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        MarkerOptions	marker	=	new	MarkerOptions()

                .position(new LatLng(0,	0)).title("Marker");

        mapa.addMarker(marker);

        LatLng	madrid	=	new	LatLng(40.417325,	-3.683081);

        CameraPosition	camPos	=	new	CameraPosition.Builder().target(madrid)

                .zoom(19)

                .bearing(45)

                .tilt(70)

                .build();

        	camUpd	=	CameraUpdateFactory.newCameraPosition(camPos);

        mapa.animateCamera(camUpd);

    }
}
