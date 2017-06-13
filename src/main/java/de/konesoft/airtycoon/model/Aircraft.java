package de.konesoft.airtycoon.model;

/**
 * Basisklasse für mögliche Erweiterungen
 *
 * @author mastercs
 */
public abstract class Aircraft {

    private final String manufactor;
    private final String type;
    private final String description;
    private final byte crew;
    private int fuel = 0;
    private final int maxFuel;
    private final short maxSpeed;
    private int flightDistance = 0;
    private final int price;
    private final short maxRange;
    private Position position = new Position(52.52000659999999F, 13.404953999999975F);

    public Aircraft(String manufactor, String type, String description, byte crew, int maxFuel, short maxSpeed, int price, short maxRange) {

        this.manufactor = manufactor;
        this.type = type;
        this.description = description;
        this.crew = crew;
        this.maxFuel = maxFuel;
        this.maxSpeed = maxSpeed;
        this.price = price;
        this.maxRange = maxRange;
    }

    public abstract void fly(Position target);

    public String getType() {
        return type;
    }

    public int getFuel() {
        return fuel;
    }

    public int getMaxFuel() {
        return maxFuel;
    }

    public short getMaxSpeed() {
        return maxSpeed;
    }

    public int getPrice() {
        return price;
    }

    public short getMaxRange() {
        return maxRange;
    }

    public Position getPosition() {
        return position;
    }

    public int getFlightDistance() {
        return flightDistance;
    }

    public String getManufactor() {
        return manufactor;
    }

    public String getDescription() {
        return description;
    }

    public byte getCrew() {
        return crew;
    }

    public void refuel(int newFuel) {
        fuel = newFuel;
    }

    public void useFuel(int fuelAmount) {

        if (fuel - fuelAmount < 0) {
            fuel = 0;
        } else {
            fuel -= fuelAmount;
        }
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setFlightDistance(int flightDistance) {
        this.flightDistance += flightDistance;
    }

}
