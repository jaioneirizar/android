package com.jaioneirizar.geolocation;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements LocationListener {


    private TextView lblAltitude;
    private TextView lblLatitude;
    private TextView lblLongitude;
    private TextView lblSpeed;


    private  String provider;

    LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        LocationListener listener = new LocationListener() ;


        locationManager.requestLocationUpdates(provider,t,distance,listener);


    }

    @Override
    public void onLocationChanged(Location location) {
        
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
