package de.konesoft.airtycoon.model;

/**
 * Modellierungsklasse für ein Airport
 *
 * @author mastercs
 */
public class Airport {

    private final byte id;
    private final String name;
    private final float lat;
    private final float lng;
    private final byte costIndex;

    public Airport(byte id, String name, float lat, float lng, byte costindex) {

        this.id = id;
        this.name = name;
        this.costIndex = costindex;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public byte getCostIndex() {
        return costIndex;
    }

    public byte getId() {
        return id;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }
}
