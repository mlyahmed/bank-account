package org.aei.bank.account;

public class BankAccount {

    private double balance;

    public void deposit(double amount) {
        balance = amount;
    }

    public double balance() {
        return balance;
    }
}
