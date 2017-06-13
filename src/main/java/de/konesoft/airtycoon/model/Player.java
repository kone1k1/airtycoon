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
    private final List<Airliner> playerFleet = new ArrayList<>();

    public Player(String name) {

        this.id = 0;
        this.name = name;
        this.account = new Bank();
    }

    public Player(byte id, String name, Bank account, List<Airliner> fleet) {

        this.id = id;
        this.name = name;
        this.account = account;
        this.playerFleet.addAll(0, fleet);
    }

    public void buyPlane(Airliner plane) {

        if (account.pay(plane.getPrice())) {
            playerFleet.add(new Airliner(plane));
        }
    }

    public void repairPlane(Airliner plane) {

        if (account.pay((int) (plane.getPrice() * 0.1))) {
            plane.repair();
        }
    }

    public void sellPlane(Aircraft plane) {

        account.deposit((int) (plane.getPrice() * 0.3));
        playerFleet.remove(playerFleet.lastIndexOf(plane));
    }

    @Override
    public String toString() {
        return "ID: " + id + System.lineSeparator() + "Name: " + name + System.lineSeparator() + "Bankguthaben: " + account.getMoney();
    }

    public List<Airliner> getPlayerFleet() {
        return playerFleet;
    }

    public Bank getAccount() {
        return account;
    }

    public Airliner getAirplane(int index) {
        return playerFleet.get(index);
    }

    public String getName() {
        return name;
    }

    public byte getId() {
        return id;
    }

}
