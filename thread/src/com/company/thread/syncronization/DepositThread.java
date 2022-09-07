package com.company.thread.syncronization;

public class DepositThread extends Thread{
    String threadName = "";
    long depositAmount = 0;
    BankAccount bankAccount;

    public DepositThread(String threadName, long depositAmount, BankAccount bankAccount) {
        this.threadName = threadName;
        this.depositAmount = depositAmount;
        this.bankAccount = bankAccount;
    }

    public void run() {
        bankAccount.deposit(threadName, depositAmount);
    }
}
