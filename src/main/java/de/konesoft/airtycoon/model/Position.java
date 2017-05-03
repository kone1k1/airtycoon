package de.konesoft.airtycoon.model;

/**
 *
 * @author mastercs
 */
public class Position {

    private final int id;
    private final boolean type;
    private float lat;
    private float lng;

    /**
     *
     * @param id unique ID
     * @param type false = Airport ; true = Airplane
     * @param lat Latitude Position
     * @param lng Longitude Position
     */
    public Position(int id, boolean type, float lat, float lng) {

        this.id = id;
        this.type = type;
        this.lat = lat;
        this.lng = lng;
    }

    public int getId() {
        return id;
    }

    public boolean getType() {
        return type;
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
