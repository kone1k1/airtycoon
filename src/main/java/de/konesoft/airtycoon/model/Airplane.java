package de.konesoft.airtycoon.model;

import de.konesoft.airtycoon.functions.Calculator;
import java.text.NumberFormat;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Modellierungsklasse für ein Flugzeug
 *
 * @author mastercs
 */
@XmlRootElement
public class Airplane implements Cloneable {

    private static final int MIN_PAX = 4;

    private final byte id;
    private final String manufacturer;
    private final String type;
    private final String description;
    private final short speed;
    private final int price;

    private short maxRange;
    private short pax;
    private short maxPax;
    private short fuel;
    private short maxFuel;
    private byte repearState;
    private int flightDistance;

    private Airport position;

    public Airplane(byte id, String manufacturer, String type, String textinfo, short speed, short maxRange, short maxPax, short maxFuel, int price, Airport position) {

        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
        this.description = textinfo;
        this.speed = speed;
        this.maxRange = maxRange;
        this.maxPax = maxPax;
        this.maxFuel = maxFuel;
        this.position = position;
        this.price = price;
        this.pax = MIN_PAX;
        this.fuel = 1000;
        this.repearState = 127;
        this.flightDistance = 0;
    }

    public boolean fly(Airport target) {

        if (target != null) {
            int dist = Calculator.calcDistance(position, target);
            int efuel = calcFuelConsumption(dist);
            if ((target.getId() == position.getId())) {
                System.out.println("Startflughafen = Zielflughafen");
                return false;
            } else if ((target.getId() != position.getId()) && (dist < maxRange) && (fuel > efuel) && !(crash_test())) {
                position = target;
                flightDistance += dist;
                pax = MIN_PAX;
                repearState--;
                System.out.println("Das Flugzeug des Typs " + manufacturer + " " + type + " flog " + dist + " km!" + System.lineSeparator() + "Dabei verbrauchte es " + efuel + " KG Kerosin." + System.lineSeparator() + "Der Restbestand im Tank ist " + fuel + " KG Kerosin.");
                return true;
            } else {
                System.out.println("Flugdistanz zu groß oder nicht genug Kerosin!");
                return false;
            }
        } else {
            return false;
        }
    }

    private int calcFuelConsumption(double distance_nm) {

        int efuel;
        efuel = (int) (pax * 0.9 * (distance_nm * 0.539957) * 0.05926);
        if (efuel < fuel) {
            fuel -= efuel;
        }
        return efuel;
    }

    private boolean crash_test() {

        byte result = (byte) (Math.random() * repearState);
        return result == 0;
    }

    public void repair() {
        repearState = 127;
    }

    @Override
    public String toString() {
        String result = "Flugzeugtyp: " + manufacturer + " " + type + System.lineSeparator() + "Anzahl Sitze: " + maxPax + System.lineSeparator() + "Preis: " + NumberFormat.getInstance().format(price) + " €";
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public short getMaxFuel() {
        return maxFuel;
    }

    public short getId() {
        return id;
    }

    public int getFlightDistance() {
        return flightDistance;
    }

    public String getDescription() {
        return description;
    }

    public short getSpeed() {
        return speed;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public short getFuel() {
        return fuel;
    }

    public short getPax() {
        return pax;
    }

    public short getMaxPax() {
        return maxPax;
    }

    public Airport getPosition() {
        return position;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public byte getRepearState() {
        return repearState;
    }

    public void setPax(short pax) {
        this.pax = pax;
    }

    public void setFuel(short fuel) {
        this.fuel = fuel;
    }

    //bereich für upgrades
    public void setMaxFuel(short maxfuel) {
        this.maxFuel = maxfuel;
    }

    public void setMaxPax(short maxPax) {
        this.maxPax = maxPax;
    }

    public void setMaxRange(short maxRange) {
        this.maxRange = maxRange;
    }

}
