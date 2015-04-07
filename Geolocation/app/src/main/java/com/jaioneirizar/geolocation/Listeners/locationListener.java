package com.jaioneirizar.geolocation.Listeners;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by cursomovil on 1/04/15.
 */

public class locationListener implements  android.location.LocationListener {


    public interface setLocationInterface {
        public void addLocation (Location location);
    }

    private setLocationInterface target;

    public locationListener(setLocationInterface target){

        this.target= target;



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
