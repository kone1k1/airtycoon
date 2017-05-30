package de.konesoft.airtycoon.model;

/**
 *
 * @author mastercs
 */
public class Position {

    private final boolean isAirplane;
    private float lat;
    private float lng;

    public Position(boolean type, float lat, float lng) {

        this.isAirplane = type;
        this.lat = lat;
        this.lng = lng;
    }

    public boolean isAirplane() {
        return isAirplane;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setLatLng(float lat, float lng) {

        this.lat = lat;
        this.lng = lng;
    }

}
