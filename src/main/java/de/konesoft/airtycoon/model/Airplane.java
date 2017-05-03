package de.konesoft.airtycoon.model;

/**
 *
 * @author mastercs
 */
public abstract class Airplane {

    private final byte id;
    private final String manufacturer;
    private final String type;
    private final String description;
    private short maxFuel;
    private short maxRange;
    private short maxPax;

    public Airplane(byte id, String manufacturer, String type, String description, short maxFuel, short maxRange, short maxPax) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
        this.description = description;
        this.maxFuel = maxFuel;
        this.maxRange = maxRange;
        this.maxPax = maxPax;
    }

    public abstract void fly(Airport target);

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

    public short getMaxPax() {
        return maxPax;
    }

    public void setMaxPax(short maxPax) {
        this.maxPax = maxPax;
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

}
