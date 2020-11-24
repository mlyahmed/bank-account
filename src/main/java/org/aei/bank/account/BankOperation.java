package org.aei.bank.account;

import java.util.Date;

public class BankOperation {

    private final OperationType type;
    private final Date date;
    private final double amount;
    private final double balance;

    protected BankOperation(OperationType type, Date date, double amount, double balance) {
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }


    public OperationType type() {
        return type;
    }

    public Date date() {
        return date;
    }

    public double amount() {
        return amount;
    }

    public double balance() {
        return balance;
    }
}
