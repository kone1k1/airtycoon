package de.konesoft.airtycoon.model;

/**
 * Basisklasse für mögliche Erweiterungen
 *
 * @author mastercs
 */
public abstract class Plane {

    private final byte id;
    private final String manufacturer;
    private final String type;
    private final String description;
    private final short speed;
    private int price;
    private short maxFuel;
    private short maxRange;

    public Plane(byte id, String manufacturer, String type, String description, short speed, int price, short maxFuel, short maxRange) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
        this.description = description;
        this.speed = speed;
        this.price = price;
        this.maxFuel = maxFuel;
        this.maxRange = maxRange;
    }

    public abstract void flyPlane(Airport target);

    public short getMaxFuel() {
        return maxFuel;
    }

    public void setMaxFuel(short maxFuel) {
        this.maxFuel = maxFuel;
    }

    public short getMaxRange() {
        return maxRange;
    }

    public void setMaxRange(short maxRange) {
        this.maxRange = maxRange;
    }

    public byte getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public short getSpeed() {
        return speed;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
