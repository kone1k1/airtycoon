/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author mastercs
 */
public class Airport {

    private final byte id;
    private final String name;
    private final float lat;
    private final float lng;
    private final byte costindex;

    /**
     *
     * @param id Einmaleige Ident Nummer des Flughafen
     * @param name Name des Flughafen
     * @param lat Höhrengrad des Flughafen
     * @param lng Breitengrad des Flughafen
     * @param costindex
     */
    public Airport(byte id, String name, float lat, float lng, byte costindex) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.costindex = costindex;
    }

    @Override
    public String toString() {
        return name;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }

    public byte getId() {
        return id;
    }
}
