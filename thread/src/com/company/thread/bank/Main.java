package com.company.thread.bank;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();

        //A rút 150000
        WithdrawThread aThread = new WithdrawThread("A", 350000,bankAccount);
        aThread.start();

        // B rút hết 200000
        DepositThread bThread = new DepositThread("B", 1000000,  bankAccount);
        bThread.start();
    }
}
