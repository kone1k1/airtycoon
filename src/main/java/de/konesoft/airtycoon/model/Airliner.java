package de.konesoft.airtycoon.model;

import de.konesoft.airtycoon.functions.Calculator;
import java.text.NumberFormat;

/**
 * Heavy Metal Airliner Class
 *
 * @author mastercs
 */
public class Airliner extends Airplane implements Cloneable {

    private static final int MIN_PAX = 4;
    private final short maxPax;
    private short pax;
    private short fuel;
    private byte repearState;
    private int flightDistance;
    private Airport position;

    public Airliner(byte id, String manufacturer, String type, String textinfo, short speed, short maxRange, short maxPax, short maxFuel, int price, Airport position) {

        super(id, manufacturer, type, textinfo, speed, price, maxFuel, maxRange);
        this.position = position;
        this.pax = MIN_PAX;
        this.fuel = 1000;
        this.repearState = 127;
        this.flightDistance = 0;
        this.maxPax = maxPax;
    }

    @Override
    public void fly(Airport target) {

        int dist = Calculator.calcDistance(position, target);
        int estimatedFuel = calcFuelConsumption(dist);

        if (target != null && position != target) {

            if ((dist < super.getMaxRange()) && (fuel > estimatedFuel) && !(crashTest())) {
                position = target;
                flightDistance += dist;
                fuel -= estimatedFuel;
                pax = MIN_PAX;
                repearState--;
                System.out.println("Das Flugzeug des Typs " + super.getManufacturer() + " " + super.getType() + " flog " + dist + " km!" + System.lineSeparator() + "Dabei verbrauchte es " + estimatedFuel + " KG Kerosin." + System.lineSeparator() + "Der Restbestand im Tank ist " + fuel + " KG Kerosin.");
            } else {
                System.out.println("Flugdistanz zu groß oder nicht genug Kerosin!");
            }
        }
    }

    private int calcFuelConsumption(double distanceNm) {
        return (int) (pax * 0.9 * (distanceNm * 0.539957) * 0.05926);
    }

    /**
     * Boolean um einen Möglichen Absturz einzuberechen mithilfe des
     * Repearstates
     *
     * @return true = Crash, false = OK
     */
    private boolean crashTest() {
        return (byte) (Math.random() * repearState) == 0;
    }

    public void repair() {
        repearState = 127;
    }

    @Override
    public String toString() {
        return "Flugzeugtyp: " + super.getManufacturer() + " " + super.getType() + System.lineSeparator() + "Anzahl Sitze: " + maxPax + System.lineSeparator() + "Preis: " + NumberFormat.getInstance().format(super.getPrice()) + " €";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
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

    public short getMaxPax() {
        return maxPax;
    }

}
