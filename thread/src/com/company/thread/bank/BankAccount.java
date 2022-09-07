package com.company.thread.bank;

public class BankAccount {
    long amount = 200000;

    public boolean checkAccountBalance(long withdrawAmount) {
        // giả lập thời gian đọc dữ liệu và kiểm tra tiền
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(withdrawAmount  <=  amount)  {
            // cho phép rút tiền
            return true;
        }

        // ko cho rút
        return false;
    }

    public void withdraw(String threadName, long withdrawAmount) {
        //In thôg tin người rút
        System.out.println(threadName + " rút: " +  withdrawAmount);

        synchronized (this)  {
            if(checkAccountBalance(withdrawAmount)) {
                // giả lập thời gian rút và
                // cập nhật số tiền còn lại vào csdl
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                amount -= withdrawAmount;
                System.out.println(threadName + " đã rút: " + withdrawAmount);
            } else {
                System.out.println(threadName + " ko thể rút");
            }
        }


        System.out.println(threadName + " số dư: " + amount);
    }

    //rút tiền khi số dư đủ
    public synchronized void withdrawWhenBalanceEnough(String threadName, long withdrawAmount) {
        System.out.println(threadName + " kiểm tra: " + withdrawAmount);

        while (!checkAccountBalance(withdrawAmount)) {
            // nếu ko đủ tiền, thì đợi đến khi có thì rút
            System.out.println(threadName + " chờ cho đủ tiền");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //đủ tiền, hoặc ko còn đợi nữa thì dc phép rút
        // giả lập tg út và cập nhật tiền
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        amount -= withdrawAmount;
        System.out.println(threadName + " đã rút: " + withdrawAmount);
    }

    public synchronized void deposit(String threadName, long depositAmount) {
        //in thoong tin người nap
        System.out.println(threadName + " đã nạp: " + depositAmount);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        amount += depositAmount;

        //đánh thức đối tượng đang ngủ và chờ có tiền thì rút
        notifyAll();
    }



}
