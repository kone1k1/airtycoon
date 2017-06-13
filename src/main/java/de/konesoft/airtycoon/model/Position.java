package de.konesoft.airtycoon.model;

/**
 *
 * @author mastercs
 */
public class Position {

    private float latitude;
    private float longitude;

    public Position(float latitude, float longitude) {
        
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLatLong(float latitude, float longitude) {
        
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Position && compare((Position) o));
    }

    @Override
    public int hashCode() {
        return super.hashCode(); 
    }

    private boolean compare(Position compare) {
        return this.latitude == compare.latitude && this.longitude == compare.longitude;
    }

}
