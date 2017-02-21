/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import functions.Calculator;

/**
 *
 * @author mastercs
 */
public class Airplane {

    private final byte id;
    private final String manufacturer;
    private final String type;
    private final String textinfo;
    private final short speed;
    private final int price;

    private short max_range;
    private short pax = 4;              // Minedstbesatzung für den Flug
    private short max_pax;
    private short fuel = 1000;          // Minedstbetankung bei kauf
    private short max_fuel;
    private byte state = 127;           // Auslieferungszustand 0 == Toalverlust
    private int flightdistance = 0;     // Auslieferungszustand
    private Airport position;

    /**
     *
     * @param id
     * @param manufacturer
     * @param type
     * @param textinfo
     * @param speed
     * @param max_range
     * @param max_pax
     * @param max_fuel
     * @param price
     * @param position
     */
    public Airplane(byte id, String manufacturer, String type, String textinfo, short speed, short max_range, short max_pax, short max_fuel, int price, Airport position) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.type = type;
        this.textinfo = textinfo;
        this.speed = speed;
        this.max_range = max_range;
        this.max_pax = max_pax;
        this.max_fuel = max_fuel;
        this.position = position;
        this.price = price;
    }

    /**
     * zu Airport target fliegen
     *
     * @param target Zielflughafen
     *
     *
     */
    public void fly(Airport target) {
        if (target != null) {
            int dist = Calculator.calcDistance(position, target);
            int efuel = calcFuelConsumption(dist);
            if ((target.getId() == position.getId())) {
                System.out.println("Startflughafen = Zielflughafen");
            } else if ((target.getId() != position.getId()) && (dist < max_range) && (fuel > efuel) && !(crash_test())) {
                position = target;
                flightdistance += dist;
                pax = 4;
                state -= 1;
                System.out.println("Das Flugzeug des Typs " + manufacturer + " " + type + " flog " + dist + " km!" + System.lineSeparator() + "Dabei verbrauchte es " + efuel + " KG Kerosin." + System.lineSeparator() + "Der Restbestand im Tank ist " + fuel + " KG Kerosin.");
            } else {
                System.out.println("Flugdistanz zu groß oder nicht genug Kerosin!");

            }
        } else {
            System.out.println("Diesen Airport gibt es leider nicht!");
        }
    }

    /**
     * Aircraft betanken
     *
     * @param amount ist die Menge an Treibstoff, die getankt werden soll. Muss
     * schon vorher festgestellt werden was der maixmale Tankzustand nicht
     * erreicht wird
     * @return true, falls Betankung erfolgreich war, ansonsten false
     */
    public boolean refuel(int amount) {
        if (amount >= 0) {
            fuel += amount;
            return true;
        } else {
            fuel -= Math.abs(amount);
            return false;
        }

    }

    /**
     * Treibstoffverbrauch berechnen
     *
     * @param distance_nm Distanz zum zielflughafen in Nautischen Meilen
     * @return Treibstoff der verbraucht wurde
     * @deprecated muss noch verbessert werden!!!!!!!
     */
    @Deprecated
    private int calcFuelConsumption(double distance_nm) {
        int efuel;

        efuel = (int) (pax * 0.9 * (distance_nm * 0.539957) * 0.05926);
        if (efuel < fuel) {
            fuel -= efuel;
        }
        return efuel;
    }

    /**
     * Bei einem Crash wird die Position auf NULL gesetzt um zu verdeutlichen
     * das der Flieger ein Totalverlust ist
     *
     * @return
     */
    private boolean crash_test() {
        byte result = (byte) (Math.random() * state);
        if (result == 0) {
            position = null;
        }
        return result == 0;
    }

    public void repair() {
        state = 127;
    }

    @Override
    public String toString() {
        String result = "Flugzeugtyp: " + manufacturer + " " + type + System.lineSeparator() + "Anzahl Sitze: " + max_pax + System.lineSeparator() + "Aktuelle Position: " + position.getName();
        return result;
    }

    public void printLocation() {
        System.out.println("Das Flugzeug befindet sich aktuell in " + position.getName() + ".");
    }

    public short getMaxFuel() {
        return max_fuel;
    }

    public short getId() {
        return id;
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

    public byte getState() {
        return state;
    }

    public void setPax(short pax) {
        this.pax = pax;
    }

//bereich für upgrades
    public void setMaxFuel(short maxfuel) {
        this.max_fuel = maxfuel;
    }

    public void setMax_pax(short max_pax) {
        this.max_pax = max_pax;
    }

    public void setMax_range(short max_range) {
        this.max_range = max_range;
    }

}
