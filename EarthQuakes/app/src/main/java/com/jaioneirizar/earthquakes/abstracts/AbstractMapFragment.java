package com.jaioneirizar.earthquakes.abstracts;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaioneirizar.earthquakes.database.EarthQuakeDB;
import com.jaioneirizar.earthquakes.model.EarthQuake;

import java.util.List;

/**
 * Created by cursomovil on 13/04/15.
 */
public  abstract  class AbstractMapFragment  extends MapFragment implements GoogleMap.OnMapLoadedCallback {

    protected EarthQuakeDB db;
    protected GoogleMap mapa;
    protected MarkerOptions marker;
    protected CameraUpdate camUpd;
    protected List<EarthQuake> earthQuakes;
    protected  SharedPreferences prefs = null;


    abstract protected  void obtenerData();
    abstract protected  void PintarMapa();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new EarthQuakeDB(getActivity());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout=super.onCreateView(inflater, container, savedInstanceState);
        getMap().setOnMapLoadedCallback(this);
        return  layout;
    }

    @Override
    public void onMapLoaded() {
        this.obtenerData();
        this.PintarMapa();

    }
    protected MarkerOptions crearMarker(EarthQuake earthQuake) {
        LatLng point = new LatLng(
                earthQuake.getCoords().getLng(),
                earthQuake.getCoords().getLat()
        );

        MarkerOptions marker = new MarkerOptions()
                .position(point)
                .title(earthQuake.getPlace())
                .snippet(String.valueOf(earthQuake.getMagnitude()));

        return marker;
    }
}
