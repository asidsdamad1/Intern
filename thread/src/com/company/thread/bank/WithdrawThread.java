package com.company.thread.bank;

public class WithdrawThread extends Thread{
    String threadName = "";
    long withdrawAmount = 0;
    BankAccount bankAccount;

    public WithdrawThread(String threadName, long withdrawAmount, BankAccount bankAccount) {
        this.threadName = threadName;
        this.withdrawAmount = withdrawAmount;
        this.bankAccount = bankAccount;
    }

    public void run() {
        bankAccount.withdrawWhenBalanceEnough(threadName, withdrawAmount);
    }
}
