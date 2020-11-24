package org.aei.bank.account;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount {

    private double balance;

    public void deposit(double amount) {
        balance += amount;
    }

    public double balance() {
        return round(balance);
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    private double round(double amount) {
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
