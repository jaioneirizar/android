package com.jaioneirizar.geolocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

import com.jaioneirizar.geolocation.Listeners.locationListener;


public class MainActivity extends ActionBarActivity implements  locationListener.setLocationInterface{


    private TextView lblAltitude;
    private TextView lblLatitude;
    private TextView lblLongitude;
    private TextView lblSpeed;


    private  String provider;
    private LocationManager locationManager;

    //LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager)getSystemService (Context.LOCATION_SERVICE);

        lblAltitude = (TextView) findViewById(R.id.lblAltitude);
        lblLatitude = (TextView) findViewById(R.id.lblLatitude);
        lblLongitude = (TextView) findViewById(R.id.lblLongitude);
        lblSpeed = (TextView) findViewById(R.id.lblSpeed);


        geoLocationProvider();
        listenLocationChanges();
    }

    private void geoLocationProvider() {

        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(true);
        criteria.setAltitudeRequired(true);

        provider= locationManager.getBestProvider(criteria, true);
        Log.d("CHANGE", provider);



    }

    private void listenLocationChanges(){

        int t=5000;
        int distance = 5;


        locationListener listener = new locationListener(this);


        locationManager.requestLocationUpdates(provider,t,distance,listener);


    }


    @Override
    public void addLocation(Location location) {

            lblLatitude.setText(String.valueOf(location.getLatitude()));
            lblLongitude.setText(String.valueOf(location.getLongitude()));
            lblAltitude.setText(String.valueOf(location.getAltitude()));
            lblSpeed.setText(String.valueOf(location.getSpeed()));
        }
    }

