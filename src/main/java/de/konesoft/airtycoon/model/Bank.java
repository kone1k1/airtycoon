package de.konesoft.airtycoon.model;

/**
 * Modellierungsklasse für eine Spielerbank
 *
 * @author mastercs
 */
public class Bank {

    private final byte id;
    private int money;
    private int credit;
    private static final int MAX_CREDIT_VALUE = 50000000;

    //Neues Spiel
    public Bank() {

        this.id = 0;
        this.money = 2500000;
        this.credit = 0;
    }

    /**
     * Die Bank eines geladenen Spielers erstellen
     *
     * @param id
     * @param money
     * @param credit
     */
    public Bank(byte id, int money, int credit) {

        this.id = id;
        this.money = money;
        this.credit = credit;
    }

    public boolean transaction(int amount) {

        if (amount <= money) {
            money -= amount;
            return true;
        } else {

            return false;
        }
    }

    public void orderCredit(int amount) {

        if (creditCheck()) {

            credit += amount;
            money += amount;
        }
    }

    public void returnCredit(int amount) {

        if (money - amount >= 0 && credit - amount >= 0) {
            money -= amount;
            credit -= amount;
        }
    }

    private boolean creditCheck() {
        return credit < MAX_CREDIT_VALUE;
    }

    public void deposit(int amount) {
        money += amount;
    }

    public int getMoney() {
        return money;
    }

    public int getCredit() {
        return credit;
    }

}
