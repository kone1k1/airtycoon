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
    private final LinkedList<Position> positionList = new LinkedList<>();

    private Map() {

    }

    public static Map getMapInstance() {

        if (mapInstance == null) {
            mapInstance = new Map();
        }
        return mapInstance;
    }

    public void getPrintedMap() {
        for (Position position : positionList) {
            System.out.println(position.getLat() + position.getLng());
        }
    }

    public void addPosition(Position posi) {

        if (posi != null) {
            positionList.addLast(posi);
        }
    }

    public void removePlanePosition(Position posi) {
        
        if (posi != null && positionList.contains(posi)) {
            positionList.remove(posi);
        }
    }

    public Position getPosition(int i) {
        return positionList.get(i);
    }

}
