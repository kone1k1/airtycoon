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
    private int credit;

    //Spiel Laden
    public Bank(byte id, int money) {
        this.id = id;
        this.money = money;
    }

    //Neues Spiel
    public Bank() {
        id = 0;
        money = 2500000;
        credit = 0;

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

    public void orderCredit(int amount) {
        credit += amount;
    }

    private void creditCheck() {

    }
}
