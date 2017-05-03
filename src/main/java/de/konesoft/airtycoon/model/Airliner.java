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
public class Airliner extends Airplane implements Cloneable {

    private static final int MIN_PAX = 4;
    private final short speed;
    private final int price;
    private short pax;
    private short fuel;
    private byte repearState;
    private int flightDistance;

    private Airport position;

    public Airliner(byte id, String manufacturer, String type, String textinfo, short speed, short maxRange, short maxPax, short maxFuel, int price, Airport position) {
        super(id, manufacturer, type, textinfo, maxFuel, maxRange, maxPax);
        this.speed = speed;
        this.position = position;
        this.price = price;
        this.pax = MIN_PAX;
        this.fuel = 1000;
        this.repearState = 127;
        this.flightDistance = 0;
    }

    @Override
    public boolean fly(Airport target) {

        if (target != null) {
            int dist = Calculator.calcDistance(position, target);
            int efuel = calcFuelConsumption(dist);
            if ((target.getId() == position.getId())) {
                System.out.println("Startflughafen = Zielflughafen");
                return false;
            } else if ((target.getId() != position.getId()) && (dist < super.getMaxRange()) && (fuel > efuel) && !(crash_test())) {
                position = target;
                flightDistance += dist;
                pax = MIN_PAX;
                repearState--;
                System.out.println("Das Flugzeug des Typs " + super.getManufacturer() + " " + super.getType() + " flog " + dist + " km!" + System.lineSeparator() + "Dabei verbrauchte es " + efuel + " KG Kerosin." + System.lineSeparator() + "Der Restbestand im Tank ist " + fuel + " KG Kerosin.");
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
        return (byte) (Math.random() * repearState) == 0;
    }

    public void repair() {
        repearState = 127;
    }

    @Override
    public String toString() {
        return "Flugzeugtyp: " + super.getManufacturer() + " " + super.getType() + System.lineSeparator() + "Anzahl Sitze: " + super.getMaxPax() + System.lineSeparator() + "Preis: " + NumberFormat.getInstance().format(price) + " €";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public short getSpeed() {
        return speed;
    }

    public int getPrice() {
        return price;
    }

    public short getFuel() {
        return fuel;
    }

    public short getPax() {
        return pax;
    }

    public Airport getPosition() {
        return position;
    }

    public void setPax(short pax) {
        this.pax = pax;
    }

    public void setFuel(short fuel) {
        this.fuel = fuel;
    }

    public int getFlightDistance() {
        return flightDistance;
    }

    public byte getRepearState() {
        return repearState;
    }

}
