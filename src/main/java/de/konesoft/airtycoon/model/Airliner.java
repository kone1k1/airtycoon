package de.konesoft.airtycoon.model;

import de.konesoft.airtycoon.functions.Calculator;

/**
 * Heavy Metal Airliner Class
 *
 * @author mastercs
 */
public class Airliner extends Aircraft {

    private short passengers = 0;
    private final short maxPassengers;
    private static final byte MAX_REPEARSTATE = 127;
    private byte repearState = MAX_REPEARSTATE;

    public Airliner(String manufactor, String type, String description, byte crew, int maxFuel, short maxSpeed, int price, short maxRange,
            short maxPassengers) {

        super(manufactor, type, description, crew, maxFuel, maxSpeed, price, maxRange);
        this.maxPassengers = maxPassengers;
    }

    public Airliner(Airliner toClone) {
        super(toClone.getManufactor(), toClone.getType(), toClone.getDescription(), toClone.getCrew(), toClone.getMaxFuel(),
                toClone.getMaxSpeed(), toClone.getPrice(), toClone.getMaxRange());
        this.maxPassengers = toClone.maxPassengers;
    }

    @Override
    public void fly(Position target) {
        if (target != null && !target.equals(getPosition())) {
            if (checkFlightConditions(target)) {
                afterFlightActions();
            }
        }
    }

    private boolean checkFlightConditions(Position target) {

        int calcFlightDistance = Calculator.calcDistance(getPosition(), target);
        int estimatedFuel = calcFuelConsumption(calcFlightDistance);

        if ((calcFlightDistance < super.getMaxRange()) && (getFuel() > estimatedFuel) && !(isCrashed())) {

            getPosition().setLatLong(target.getLatitude(), target.getLongitude());
            setFlightDistance(calcFlightDistance);
            useFuel(estimatedFuel);

            System.out.println("Das Flugzeug des Typs " + super.getManufactor() + " " + super.getType() + " flog " + calcFlightDistance + " km!" + System.lineSeparator() + "Dabei verbrauchte es " + estimatedFuel + " KG Kerosin." + System.lineSeparator() + "Der Restbestand im Tank ist " + getFuel() + " KG Kerosin.");
            return true;

        } else {

            System.out.println("Flugdistanz zu groß oder nicht genug Kerosin!");
            return false;
        }
    }

    private void afterFlightActions() {
        passengers = 0;
        repearState--;
    }

    private int calcFuelConsumption(double distanceNm) {
        return (int) (getPassengersAndCrew() * 0.9 * (distanceNm * 0.539957) * 0.05926);
    }

    private boolean isCrashed() {
        return (byte) (Math.random() * repearState) == 0;
    }

    @Override
    public String toString() {
        return super.getManufactor() + " " + super.getType() + " Preis: " + super.getPrice();
    }

    public void loadPassengers(short passengers) {

        if (this.passengers + passengers >= maxPassengers) {

            this.passengers = maxPassengers;
        } else {
            this.passengers += passengers;
        }
    }

    public void unLoadPassengers() {
        this.passengers = 0;
    }

    public short getPassengersAndCrew() {
        return (short) (passengers + super.getCrew());
    }

    public short getMaxPassengers() {
        return maxPassengers;
    }

    public byte getRepearState() {
        return repearState;
    }

    public byte getMAX_REPEARSTATE() {
        return MAX_REPEARSTATE;
    }

    public short getPassengers() {
        return passengers;
    }

    public void repair() {
        this.repearState = MAX_REPEARSTATE;
    }

}
