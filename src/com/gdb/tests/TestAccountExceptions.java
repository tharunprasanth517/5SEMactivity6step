package com.gdb.tests;

import com.gdb.domain.Account;
import com.gdb.exceptions.*;

public class TestAccountExceptions {

    public static void main(String[] args) {

        System.out.println("=== Activity 6: Exception Hierarchy Test ===");

        Account acc = new Account(
                "ACC1001",
                "Rajesh Sharma",
                28,
                5000.0,
                "SAVINGS",
                "ACTIVE",
                "1234"
        );

        // Test 1: Invalid PIN
        try {
            acc.withdraw(1000.0, "9999");
        } catch (InvalidPinException e) {
            System.out.println(
                    "Caught InvalidPinException: " + e.getMessage()
            );
        } catch (AccountException e) {
            System.out.println(
                    "Caught unexpected: " + e.getMessage()
            );
        }

        // Test 2: Inactive Account
        acc.suspend();

        try {
            acc.withdraw(1000.0, "1234");
        } catch (InactiveAccountException e) {
            System.out.println(
                    "Caught InactiveAccountException: " + e.getMessage()
            );
        } catch (AccountException e) {
            System.out.println(
                    "Caught unexpected: " + e.getMessage()
            );
        }

        // Activate account
        acc.activate();

        // Test 3: Invalid Amount
        try {
            acc.withdraw(-500.0, "1234");
        } catch (InvalidAmountException e) {
            System.out.println(
                    "Caught InvalidAmountException: " + e.getMessage()
            );
        } catch (AccountException e) {
            System.out.println(
                    "Caught unexpected: " + e.getMessage()
            );
        }

        // Test 4: Insufficient Balance
        try {
            acc.withdraw(10000.0, "1234");
        } catch (InsufficientBalanceException e) {
            System.out.println(
                    "Caught InsufficientBalanceException: " + e.getMessage()
            );
        } catch (AccountException e) {
            System.out.println(
                    "Caught unexpected: " + e.getMessage()
            );
        }

        // Test 5: Closed Account - General Exception Catch
        acc.close();

        try {
            acc.withdraw(1000.0, "1234");
        } catch (AccountException e) {
            System.out.println(
                    "Caught AccountException polymorphically: "
                    + e.getClass().getSimpleName()
                    + " - " + e.getMessage()
            );
        }

        System.out.println("=== Activity 6 Test Completed ===");
    }
}