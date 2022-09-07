package com.company.thread.deadlock;

public class MainDeadlock {
    public static void main(String[] args) {
        BankAccount aAccount = new BankAccount("Tài khoản A");
        BankAccount bAccount = new BankAccount("Tài khoản B");

        Thread aThread = new Thread() {
            @Override
            public void run() {
                aAccount.tranferTo(bAccount, 100000);
            }
        };

        Thread bThread = new Thread() {
            @Override
            public void run() {
                bAccount.tranferTo(aAccount, 50000);
            }
        };

        aThread.start();
        bThread.start();


    }
}
