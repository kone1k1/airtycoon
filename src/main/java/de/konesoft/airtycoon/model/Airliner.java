package de.konesoft.airtycoon.model;

import de.konesoft.airtycoon.functions.Calculator;
import java.text.NumberFormat;

/**
 * Heavy Metal Airliner Class
 *
 * @author mastercs
 */
public class Airliner extends Plane implements Cloneable {

    private static final short MIN_PASSANAGERS = 4;
    private static final byte MAX_REPEARSTATE = 127;
    private final short maxPassengers;
    private short activePassangers;
    private short planeFuel = 1000;
    private byte repearState = MAX_REPEARSTATE;
    private int flightDistance = 0;
    private Airport position;

    public Airliner(byte id, String manufacturer, String type, String textinfo, short speed, short maxRange, short maxPax, short maxFuel, int price, Airport position) {

        super(id, manufacturer, type, textinfo, speed, price, maxFuel, maxRange);
        this.position = position;
        this.activePassangers = MIN_PASSANAGERS;
        this.maxPassengers = maxPax;
    }

    @Override
    public void flyPlane(Airport target) {

        if (target != null && position != target) {
            if (checkFlightConditions(target)) {
                afterFlightActions();
            }
        }
    }

    private boolean checkFlightConditions(Airport target) {

        int calcFlightDistance = Calculator.calcDistance(position, target);
        int estimatedFuel = calcFuelConsumption(calcFlightDistance);
        if ((calcFlightDistance < super.getMaxRange()) && (planeFuel > estimatedFuel) && !(isCrashed())) {
            position = target;
            flightDistance += calcFlightDistance;
            planeFuel -= estimatedFuel;
            System.out.println("Das Flugzeug des Typs " + super.getManufacturer() + " " + super.getType() + " flog " + calcFlightDistance + " km!" + System.lineSeparator() + "Dabei verbrauchte es " + estimatedFuel + " KG Kerosin." + System.lineSeparator() + "Der Restbestand im Tank ist " + planeFuel + " KG Kerosin.");
            return true;
        } else {
            System.out.println("Flugdistanz zu groß oder nicht genug Kerosin!");
            return false;
        }
    }

    private void afterFlightActions() {
        activePassangers = MIN_PASSANAGERS;
        repearState--;
    }

    private int calcFuelConsumption(double distanceNm) {
        return (int) (activePassangers * 0.9 * (distanceNm * 0.539957) * 0.05926);
    }

    private boolean isCrashed() {
        return (byte) (Math.random() * repearState) == 0;
    }

    public void repair() {
        repearState = MAX_REPEARSTATE;
    }

    @Override
    public String toString() {
        return "Flugzeugtyp: " + super.getManufacturer() + " " + super.getType() + System.lineSeparator() + "Anzahl Sitze: " + maxPassengers + System.lineSeparator() + "Preis: " + NumberFormat.getInstance().format(super.getPrice()) + " €";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public short getPlaneFuel() {
        return planeFuel;
    }

    public short getActivePassangers() {
        return activePassangers;
    }

    public void setActivePassangers(short activePassangers) {
        this.activePassangers = activePassangers;
    }

    public void setPlaneFuel(short planeFuel) {
        this.planeFuel = planeFuel;
    }

    public int getFlightDistance() {
        return flightDistance;
    }

    public byte getRepearState() {
        return repearState;
    }

    public short getMaxPassengers() {
        return maxPassengers;
    }

    public Airport getPosition() {
        return position;
    }

}
