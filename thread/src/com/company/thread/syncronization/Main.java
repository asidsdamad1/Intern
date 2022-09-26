package com.company.thread.syncronization;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        BankAccount bankAccount1 = new BankAccount();

        // default 200000
        //A rút 350000
        WithdrawThread aThread = new WithdrawThread("A", 350000,bankAccount);
        aThread.start();

        // B nạp
        DepositThread bThread = new DepositThread("B", 200000,  bankAccount);
        bThread.start();
    }
}
