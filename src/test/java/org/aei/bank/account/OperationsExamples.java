package org.aei.bank.account;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.Arrays;
import java.util.stream.Stream;

public final class OperationsExamples {

    static class GivenOperation {
        OperationType type;
        double amount;
        double balance;

        public GivenOperation(OperationType type, double amount, double balance) {
            this.type = type;
            this.amount = amount;
            this.balance = balance;
        }
    }

    static class AccountHistoryExamples implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of( // Each example must be read as a chain
                    Arguments.of(Arrays.asList(
                            new GivenOperation(OperationType.DEPOSIT, 100, 100),
                            new GivenOperation(OperationType.DEPOSIT, 10, 110),
                            new GivenOperation(OperationType.WITHDRAW, 90, 20),
                            new GivenOperation(OperationType.DEPOSIT, 1000, 1020),
                            new GivenOperation(OperationType.WITHDRAW, 100, 920)
                    ), 920),
                    Arguments.of(Arrays.asList(
                            new GivenOperation(OperationType.DEPOSIT, 1000, 1000),
                            new GivenOperation(OperationType.WITHDRAW, 900, 100),
                            new GivenOperation(OperationType.WITHDRAW, 100, 0),
                            new GivenOperation(OperationType.DEPOSIT, 20, 20),
                            new GivenOperation(OperationType.DEPOSIT, 980, 1000)
                    ), 1000),
                    Arguments.of(Arrays.asList(
                            new GivenOperation(OperationType.DEPOSIT, 13321.26, 13321.26),
                            new GivenOperation(OperationType.WITHDRAW, 980, 12341.26),
                            new GivenOperation(OperationType.WITHDRAW, 109.02, 12232.24),
                            new GivenOperation(OperationType.WITHDRAW, 2398.32, 9833.92),
                            new GivenOperation(OperationType.WITHDRAW, 7845.98, 1987.94)
                    ), 1987.94)
            );
        }
    }
}
