package de.konesoft.airtycoon.model;

/**
 * Modellierungsklasse für ein Airport
 *
 * @author mastercs
 */
public class Airport {

    private final String name;
    private final byte costIndex;
    private final Position position;

    public Airport(String name, byte costIndex, Position position) {
        this.name = name;
        this.costIndex = costIndex;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Flughafen: " + name;
    }

    public String getName() {
        return name;
    }

    public byte getCostIndex() {
        return costIndex;
    }

    public Position getPosition() {
        return position;
    }

}
