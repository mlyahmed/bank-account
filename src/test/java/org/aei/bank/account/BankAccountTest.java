package org.aei.bank.account;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    public void setup() {
        account = new BankAccount();
    }

    static class DepositExamples implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(Arrays.asList(120.00, 300.00, 500.00), 920.00),
                    Arguments.of(Arrays.asList(333.98, 85.23, 1200.00, 1.85, 905.01), 2526.07),
                    Arguments.of(Arrays.asList(930.12, 0.12), 930.24)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(DepositExamples.class)
    public void when_deposit_an_amount_then_it_is_saved(List<Double> deposits, double expectedBalance) {
        deposits.forEach(amount -> account.deposit(amount));
        assertEquals(expectedBalance, account.balance());
    }

    static class WithdrawExamples implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(5000.12, Arrays.asList(120.00, 300.00, 500.00), 4080.12),
                    Arguments.of(3033.90, Arrays.asList(333.98, 85.23, 1200.00, 1.85, 905.01), 507.83),
                    Arguments.of(12369.23, Arrays.asList(930.12, 0.12), 11438.99)
            );
        }
    }

    @ParameterizedTest
    @ArgumentsSource(WithdrawExamples.class)
    public void when_withdraw_an_amount_then_it_is_saved(double currentBalance, List<Double> withdraws, double remainedBalance) {
        account.deposit(currentBalance);
        withdraws.forEach(amount -> account.withdraw(amount));
        assertEquals(remainedBalance, account.balance());
    }

}
