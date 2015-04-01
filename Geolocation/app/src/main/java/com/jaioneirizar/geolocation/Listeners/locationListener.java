package com.jaioneirizar.geolocation.Listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by cursomovil on 1/04/15.
 */
public class locationListener implements  LocationListener {


    public interface setLOcationInterface {
        public void addLocation (Location location)
    }

    private locationListener(setLOcationInterface target){

    }



    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();

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
