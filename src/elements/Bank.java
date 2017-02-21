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
public class Bank {

    private final byte id;
    private int money;

    /**
     * Laden eines Spielstandes
     *
     * @param id gleich der spieler id
     * @param money aktuelles guthaben
     */
    public Bank(byte id, int money) {
        this.id = id;
        this.money = money;
    }

    /**
     * Neuer Spielstand
     */
    public Bank() {
        id = 0;
        money = 2500000;

    }

    public boolean transaction(int amount) {
        if (amount < money) {
            money -= amount;
            return true;
        } else {
            return false;
        }
    }

    public void deposit(int amount) {
        money += amount;
    }

    public int getMoney() {
        return money;
    }
}
