package org.aei.bank.account;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    @ParameterizedTest
    @ValueSource(doubles = {12.3, 54.23, 100})
    public void when_deposit_an_amount_then_it_is_saved(double amount) {
        BankAccount account = new BankAccount();
        account.deposit(amount);
        assertEquals(account.balance(), amount);
    }

}
