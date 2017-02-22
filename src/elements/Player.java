/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

/**
 *
 * @author mastercs
 */
public class Player {

    private final byte id;
    private final String name;

    private final Bank account;
    private Airplane[] fleet = new Airplane[20]; //flotte maximal 20

    /**
     * Laden eines Spielstandes
     *
     * @param id Einmalige Ident Nummer für den Spieler
     * @param name Name des Spieler
     * @param account Bank des Spielers
     * @param fleet Flugzeugflotte des Spielers
     */
    public Player(byte id, String name, Bank account, Airplane[] fleet) {

        this.id = id;
        this.name = name;
        this.account = account;
        this.fleet = fleet;
    }

    /**
     * Neues Spiel
     *
     * @param name Name des Spieler
     */
    public Player(String name) {
        id = 0;
        this.name = name;
        account = new Bank();
    }

    public boolean buy_plane(Airplane plane) {

        if (account.transaction(plane.getPrice())) {
            for (int i = 0; i < fleet.length - 1; i++) {
                if (fleet[i] == null) {
                    fleet[i] = plane;
                    break;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param plane_id Flugzeugposition im Array
     * @deprecated fixwert muss noch angepasst werden
     */
    @Deprecated
    public void sell_plane(int plane_id) {

        account.deposit((int) (fleet[plane_id].getPrice() * 0.3));      //später fixwert du wartungsindex ersetzen um angemessenen wert zu finden  
        fleet[plane_id] = null;
    }

    @Override
    public String toString() {
        return "ID: " + id + System.lineSeparator() + "Name: " + name + System.lineSeparator() + "Bankguthaben: " + account.getMoney();
    }

    public Airplane[] getFleet() {

        return fleet;
    }

    public Bank getAccount() {

        return account;
    }

    public Airplane getAirplane(int index) {

        return fleet[index];
    }

    /**
     * Für erstmaliges Setzen eines Flugzeuges an 1. Stelle
     *
     * @param plane
     */
    public void setFleet(Airplane plane) {

        fleet[0] = plane;
    }

}
