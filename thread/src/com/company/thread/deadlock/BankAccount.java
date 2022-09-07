package com.company.thread.deadlock;

public class BankAccount {
    long amount = 200000;
    String accountName = "";

    public BankAccount(String accountName) {
        this.accountName = accountName;
    }

    //rút tiền
    public synchronized void withdraw(long withdrawAmount) {
        System.out.println(accountName + " đang rút tiền...");
        amount -= withdrawAmount;
    }

    //nạp tiền
    public synchronized void deposit(long depositAmount) {
        System.out.println(accountName + " đang nạp tiền...");
        amount += depositAmount;
    }

    //chuyển tiền qua tài khoản
    public void tranferTo(BankAccount toAccount, long tranferAmount) {
        synchronized (this) {
            this.withdraw(tranferAmount);

            synchronized (toAccount) {
                toAccount.deposit(tranferAmount);
            }
            System.out.println("số tiền của " + accountName + " là: " + amount);
        }
    }
}

