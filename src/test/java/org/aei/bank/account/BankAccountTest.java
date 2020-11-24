package org.aei.bank.account;


import org.aei.bank.account.OperationsExamples.GivenOperation;
import org.aei.bank.account.OperationsExamples.AccountHistoryExamples;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import static org.aei.bank.account.OperationType.DEPOSIT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    public void setup() {
        account = new BankAccount();
    }

    @ParameterizedTest
    @ArgumentsSource(AccountHistoryExamples.class)
    public void when_operations_are_applied_the_balance_remains_valid(List<GivenOperation> operations, double balance) {
        when_applying_operations(operations);

        assertEquals(balance, account.balance());
    }


    @ParameterizedTest
    @ArgumentsSource(AccountHistoryExamples.class)
    public void when_generate_a_statement_it_must_show_all_operations(List<GivenOperation> operations, double balance) {
        when_applying_operations(operations);

        BankStatement statement = account.statement();

        assertEquals(balance, statement.balance());
        assertEquals(operations.size(), statement.operations().size());
        IntStream.range(0, operations.size()).forEach(i -> {
            assertEquals(operations.get(i).type, statement.operations().get(i).type());
            assertEquals(operations.get(i).amount, statement.operations().get(i).amount());
            assertEquals(operations.get(i).balance, statement.operations().get(i).balance());
            assertSameDay(new Date(), statement.operations().get(i).date()); //TODO: test at a frozen date.
        });
    }


    private void when_applying_operations(List<GivenOperation> operations) {
        operations.forEach(o -> {
            if (o.type == DEPOSIT) {
                account.deposit(o.amount);
            } else {
                account.withdraw(o.amount);
            }
        });
    }

    private void assertSameDay(Date expected, Date actual) {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        assertEquals(formatter.format(expected), formatter.format(actual), "Not in the same day.");
    }

}
