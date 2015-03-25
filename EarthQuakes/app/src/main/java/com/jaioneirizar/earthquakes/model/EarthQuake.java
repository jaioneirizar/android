package com.jaioneirizar.earthquakes.model;

import java.util.Date;

/**
 * Created by cursomovil on 25/03/15.
 */
public class EarthQuake {

    private String _id;
    private String place;
    private Coordinate coords;
    private Date time;
    private double magnitude;
    private String url;


    public EarthQuake(String _id, String place, Coordinate coords, Date time, double magnitude) {
        this._id = _id;
        this.place = place;
        this.coords = coords;
        this.time = time;
        this.magnitude = magnitude;
    }

    public EarthQuake(){

    }

  @Override
    public String toString(){
        return this.getPlace();
    }

    public String get_id() {
        return _id;
    }

    public String getPlace() {
        return place;
    }

    public Coordinate getCoords() {
        return coords;
    }

    public Date getTime() {
        return time;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setCoords(Coordinate coords) {
        this.coords = coords;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTime(long time) {
        this.time = new Date(time);
    }

    public void setMagnitude(double magnitude) {
        this.magnitude = magnitude;
    }


}
