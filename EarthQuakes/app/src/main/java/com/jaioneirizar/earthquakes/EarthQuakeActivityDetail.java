package com.jaioneirizar.earthquakes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jaioneirizar.earthquakes.database.EarthQuakeDB;
import com.jaioneirizar.earthquakes.fragments.EarthQuakeListFragment;
import com.jaioneirizar.earthquakes.model.EarthQuake;
public class EarthQuakeActivityDetail extends ActionBarActivity {

    private EarthQuake earthQuake;
    private TextView lblid;
    private TextView lblsite;
    private TextView lblurl;
    private GoogleMap mapa;
    private MarkerOptions marker;
    private CameraPosition camPos;
    private CameraUpdate camUpd;
    private Double Lng;
    private Double Lat;


    private EarthQuakeDB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_earth_quake_activity_detail);
        db = new EarthQuakeDB(this);


      /*  lblid= (TextView) findViewById(R.id.lblid);
        lblsite= (TextView) findViewById(R.id.lblsite);
        lblurl= (TextView) findViewById(R.id.lblurl);*/
        Intent detailIntent = getIntent();
       // Log.d("CHANGE", String.valueOf(lblid));
      //  earthQuake= detailIntent.getParcelableExtra(EarthQuakeListFragment.EARTHQUAKES_ITEM);
        //earthQuake= detailIntent.getExtras(EarthQuakeListFragment.EARTHQUAKES_ITEM);

        String id = detailIntent.getStringExtra(EarthQuakeListFragment.EARTHQUAKES_ITEM);
        earthQuake = db.getById(id);

        //Log.d("change", earthQuake.get_id());
      //  populateView(earthQuake);
        MapView(earthQuake);

    }




    protected void MapView(EarthQuake earthquake) {


        if (mapa == null) {
            // Try to obtain the map from the SupportMapFragment.
            mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mapa != null) {

                Lng=earthQuake.getCoords().getLng();
                Lat=earthQuake.getCoords().getLat();
                String Place = earthQuake.getPlace();

                        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                MarkerOptions marker = new MarkerOptions()



                        .position(new LatLng(Lng,Lat)).title(Place);

                mapa.addMarker(marker);

                LatLng position = new LatLng(Lng,Lat);
                CameraPosition camPos = new CameraPosition.Builder().target(position)

                        .zoom(12)

                       // .bearing(45)

                     //   .tilt(70)

                        .build();

                camUpd = CameraUpdateFactory.newCameraPosition(camPos);

                mapa.animateCamera(camUpd);

            }
        }


    }




    private void populateView(EarthQuake earthquake){

        lblid.setText(earthQuake.get_id());
        lblsite.setText(earthQuake.getPlace());
        lblurl.setText(earthQuake.getUrl());
       // lblurl.setText(Html.fromHtml("<a href=" + earthQuake.getUrl() + "></a>"));

    }
}
