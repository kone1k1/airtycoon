/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.konesoft.airtycoon.model;

public class Bank {

    private final byte id;
    private int money;
    private int credit;
    private static final int MAX_CREDIT_VALUE = 50000000;

    //Spiel Laden
    public Bank(byte id, int money) {

        this.id = id;
        this.money = money;
    }

    //Neues Spiel
    public Bank() {

        this.id = 0;
        this.money = 2500000;
        this.credit = 0;
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
