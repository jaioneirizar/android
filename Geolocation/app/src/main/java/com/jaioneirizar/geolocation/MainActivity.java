package com.jaioneirizar.geolocation;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {


    private TextView lblAltitude;
    private TextView lblLatitude;
    private TextView lblLongitude;
    private TextView lblSpeed;


    private  String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblAltitude = (TextView) findViewById(R.id.lblAltitude);
        lblLatitude = (TextView) findViewById(R.id.lblLatitude);
        lblLongitude = (TextView) findViewById(R.id.lblLongitude);
        lblSpeed = (TextView) findViewById(R.id.lblSpeed);


        geoLocationProvider();
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

}
