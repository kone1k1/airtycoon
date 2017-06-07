package de.konesoft.airtycoon.model;

import java.util.LinkedList;

/**
 * Weltansicht mit allen Flugzeugpositionen und Airports, nur eine Instance
 * möglich
 *
 * @author mastercs
 */
public class Map {

    private static Map mapInstance;
    private static final int HEIGHT = 500;
    private static final int WIDHT = 500;
    private final LinkedList<Position> planePositions = new LinkedList<>();
    private final LinkedList<Airport> airportPositions = new LinkedList<>();

    private Map() {

    }

    public static Map getMapInstance() {

        if (mapInstance == null) {
            mapInstance = new Map();
        }
        return mapInstance;
    }

    public void drawMap() {

    }

    public void addAirport(Airport airport) {

        if (airport != null) {
            airportPositions.add(airport);
            System.out.println(airportPositions.toString());
        }
    }

    public void addPosition(Position posi) {

        if (posi != null) {
            planePositions.add(posi);
        }
    }

    public void removePlanePosition(Position posi) {

        if (posi != null && planePositions.contains(posi)) {
            planePositions.remove(posi);
        }
    }

}
