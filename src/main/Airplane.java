/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import functions.Calculator;
import java.text.NumberFormat;

/**
 *
 * @author mastercs
 */
public class Airplane implements Cloneable {

    private final byte id;
    private final String manufacturer;
    private final String type;
    private final String textinfo;
    private final short speed;
    private final int price;

    private short max_range;
    private short pax = 4;
    private short max_pax;
    private short fuel = 1000;
    private short max_fuel;
    private byte repearstate = 127;
    private int flightdistance = 0;

    private Airport position;

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
                repearstate--;
                System.out.println("Das Flugzeug des Typs " + manufacturer + " " + type + " flog " + dist + " km!" + System.lineSeparator() + "Dabei verbrauchte es " + efuel + " KG Kerosin." + System.lineSeparator() + "Der Restbestand im Tank ist " + fuel + " KG Kerosin.");
            } else {
                System.out.println("Flugdistanz zu groß oder nicht genug Kerosin!");

            }
        } else {
            System.out.println("Diesen Airport gibt es leider nicht!");
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
        byte result = (byte) (Math.random() * repearstate);
        return result == 0;
    }

    public void repair() {
        repearstate = 127;
    }

    @Override
    public String toString() {
        String result = "Flugzeugtyp: " + manufacturer + " " + type + System.lineSeparator() + "Anzahl Sitze: " + max_pax + System.lineSeparator() + "Preis: " + NumberFormat.getInstance().format(price) + " €";
        return result;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }

    public short getMaxFuel() {
        return max_fuel;
    }

    public short getId() {
        return id;
    }

    public int getFlightdistance() {
        return flightdistance;
    }

    public String getTextinfo() {
        return textinfo;
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

    public short getMax_pax() {
        return max_pax;
    }

    public Airport getPosition() {
        return position;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public byte getRepearstate() {
        return repearstate;
    }

    public void setPax(short pax) {
        this.pax = pax;
    }

    public void setFuel(short fuel) {
        this.fuel = fuel;
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
