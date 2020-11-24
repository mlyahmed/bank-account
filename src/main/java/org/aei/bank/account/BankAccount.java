package org.aei.bank.account;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BankAccount {

    private double balance;
    private final List<BankOperation> register;

    public BankAccount() {
        this.register = new LinkedList<>();
    }

    public void deposit(double amount) {
        balance += amount;
        registerOperation(amount, OperationType.DEPOSIT);
    }

    public double balance() {
        return round(balance);
    }

    public void withdraw(double amount) {
        balance -= amount;
        registerOperation(amount, OperationType.WITHDRAW);
    }

    public BankStatement statement() {
        return new BankStatement(new LinkedList<>(register), round(balance));
    }

    private void registerOperation(double amount, OperationType deposit) {
        register.add(new BankOperation(deposit, new Date(), round(amount), round(balance)));
    }

    private double round(double amount) {
        return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
