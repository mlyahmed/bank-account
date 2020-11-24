package org.aei.bank.account;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    public void setup() {
        account = new BankAccount();
    }

    @ParameterizedTest
    @ValueSource(doubles = {12.3, 54.23, 100})
    public void when_deposit_an_amount_then_it_is_saved(double amount) {
        account.deposit(amount);
        assertEquals(account.balance(), amount);
    }

    @ParameterizedTest
    @CsvSource({"100, 10", "500, 500", "1028.33, 28.31"})
    public void when_withdraw_an_amount_then_it_is_saved(double currentBalance, double amount) {
        account.deposit(currentBalance);
        account.withdraw(amount);
        assertEquals(account.balance(), currentBalance - amount);
    }

}
