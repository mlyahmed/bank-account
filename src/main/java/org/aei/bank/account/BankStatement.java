package org.aei.bank.account;

import java.util.List;

public class BankStatement {

    private final List<BankOperation> operations;
    private final double balance;

    protected BankStatement(List<BankOperation> operations, double balance) {
        this.operations = operations;
        this.balance = balance;
    }

    public List<BankOperation> operations() {
        return operations;
    }

    public double balance() {
        return balance;
    }

}
