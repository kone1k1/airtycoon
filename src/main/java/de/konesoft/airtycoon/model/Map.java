package de.konesoft.airtycoon.model;

import java.util.HashSet;

/**
 * Weltansicht mit allen Flugzeugpositionen und Airports, nur eine Instance
 * möglich
 *
 * @author mastercs
 */
public class Map {

    private static Map mapInstance;

    private HashSet<Positionable> positionList = new HashSet();

    private Map() {

    }

    public static Map getMapInstance() {

        if (mapInstance == null) {
            mapInstance = new Map();
        }
        return mapInstance;
    }

    public void addPosition(Positionable posi) {

        if (posi != null) {
            positionList.add(posi);
        }
    }

    public void removePosition(Positionable posi) {

        if (posi != null && positionList.contains(posi)) {
            positionList.remove(posi);
        }
    }

    public void updatePositin(Positionable posi){
    
    
    }
}
