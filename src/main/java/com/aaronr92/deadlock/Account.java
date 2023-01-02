package com.aaronr92.deadlock;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Account {
    private int balance;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
//        if (balance < amount)
//            throw new InsufficientFundsException("You dont have enough money!");
        balance -= amount;
    }

    public static void transfer(Account sender, Account payee, int amount) {
        sender.withdraw(amount);
        payee.deposit(amount);
    }
}
