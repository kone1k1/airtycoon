package de.konesoft.airtycoon.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mastercs
 */
public class Player {

    private final byte id;
    private final String name;

    private final Bank account;
    private List<Airplane> fleet = new ArrayList<>();

    /**
     * Neues Spiel
     *
     * @param name Name des Spieler
     */
    public Player(String name) {

        this.id = 0;
        this.name = name;
        this.account = new Bank();
    }

    /**
     * Laden eines Spielstandes
     *
     * @param id Einmalige Ident Nummer für den Spieler
     * @param name Name des Spieler
     * @param account Bank des Spielers
     * @param fleet
     */
    public Player(byte id, String name, Bank account, List<Airplane> fleet) {

        this.id = id;
        this.name = name;
        this.account = account;
        this.fleet.addAll(0, fleet);
    }

    public void buy_plane(Airplane plane) {

        if (account.transaction(plane.getPrice())) {
            try {
                fleet.add((Airplane) plane.clone());
            } catch (CloneNotSupportedException ex) {
                ex.getStackTrace();
            }
        }
    }

    public void repair(Airplane plane) {

        if (account.transaction((int) (plane.getPrice() * 0.1))) {
            plane.repair();
        }
    }

    public void sell_plane(Airplane plane) {

        account.deposit((int) (plane.getPrice() * 0.3));
        fleet.remove(fleet.lastIndexOf(plane));
    }

    @Override
    public String toString() {
        return "ID: " + id + System.lineSeparator() + "Name: " + name + System.lineSeparator() + "Bankguthaben: " + account.getMoney();
    }

    public List<Airplane> getFleet() {
        return fleet;
    }

    public Bank getAccount() {
        return account;
    }

    public Airplane getAirplane(int index) {
        return fleet.get(index);
    }

    public String getName() {
        return name;
    }

    public byte getId() {
        return id;
    }

}
