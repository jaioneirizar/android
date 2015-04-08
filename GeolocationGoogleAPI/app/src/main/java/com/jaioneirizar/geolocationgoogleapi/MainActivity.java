package com.jaioneirizar.geolocationgoogleapi;

import android.location.Location;
import android.location.LocationListener;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;




import org.w3c.dom.Text;


public class MainActivity extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private boolean conectado = false;
    private Location mLastLocation;
    private GoogleApiClient myGoogleApiClient;
    private TextView position;
    private TextView estado;
    private Location location;
    private LocationRequest myLocationequest;



    public void buildGoogleApiClient() {
        myGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();


        myLocationequest = LocationRequest.create()
                .setPriority(LocationRequest
                        .PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1 * 1000);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conectado = false;
        estado = (TextView) findViewById(R.id.estado);
        position = (TextView) findViewById(R.id.position);
        buildGoogleApiClient();




    }


    @Override
    public void onConnected(Bundle bundle) {

        conectado = true;

    }

    @Override
    public void onConnectionSuspended(int i) {
        conectado = false;

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        conectado = false;

    }

    @Override
    protected void onStart() {
        super.onStart();
        myGoogleApiClient.connect();
        Log.i("TAG", "Location services connected.");
        getPosition();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        myGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void getPosition() {
        if (conectado) {
            location = LocationServices.FusedLocationApi.getLastLocation( myGoogleApiClient);
            if(location==null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(myGoogleApiClient, myLocationequest, (com.google.android.gms.location.LocationListener) this);

            }
            estado.setText("Conectado");



                position.setText("Lat: " + String.valueOf(location.getLatitude()) + ", " + "Lon: " + String.valueOf(location.getLongitude()));
                Log.d("gps", String.valueOf(location.getLatitude()));

        }
    }
}


