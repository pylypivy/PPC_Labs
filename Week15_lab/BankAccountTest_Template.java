package org.example;



// These are imports you may or may not need depending
// on where and how you are running the tests
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions;



class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Starts each test with a fresh account of 100.0
        account = new BankAccount(100.0);
    }

    /* 1. Add an @AfterEach annotation and method to delete the current bank account to make it available for garbage collection */
    @AfterEach
    void cleanUp() {
        account = null;
    }
    /* 2. A deposit $50 and check that the balance is 150 */
    @Test
    void testDeposit() {
        account.deposit(50.0);
        assertEquals(150.0,account.getBalance(),
               "After depositing 50$, balance should become 150$.");

    }

    @Test
    void testWithdraw() {
        /* 3. withdraw $40 and check that the balance is $60; remember that each test is done on a fresh instance of bank account */
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(),
                "After withdrawing 40$, balance should become 60$.");
    }

    @Test
    void testInvalidDeposit() {
        /* 4. Deposit a negative amount and check if an exception is thrown */
        assertThrows(IllegalArgumentException.class,() -> account.deposit(-15.0),
                "The deposit amount cannot be less than 0$");
    }

    @Test
    void testOverdraft() {
        /* 5. Verify that Withdrawing more than the current balance
         throws an exception */
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(120.0),
                "Cannot withdraw the amount larger than the balance.");
    }

    @Test
    /* 6. Add a test to check that an Exception is thrown when
     trying to create a new bank account with a negative initial balance */
    void testNegativeInitialBalance() {
        assertThrows(IllegalArgumentException.class, () -> {
            new BankAccount(-100.0);}, "Initial balance cannot be negative");
    }
    @Test
    void testTransfer(){
        BankAccount account2 = new BankAccount(100.0);
        account.transfer(account2,50.0);
        assertAll(() -> assertEquals(150.0,account.getBalance(),
                "After the transfer balance of the 'account' should be 150$"),
                () -> assertEquals(50.0,account2.getBalance(),
                          "After the transfer balance of the 'account2' should be 50$"));
    }
}

