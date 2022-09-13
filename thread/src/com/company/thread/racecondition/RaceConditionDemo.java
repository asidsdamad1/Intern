package com.company.thread.racecondition;

class Counter implements Runnable{
    private int count = 0;

    public void decrement() {
        count--;
    }

    public void increment()  {

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public int getCount() {
        return count;
    }
    @Override
    public void run() {
        synchronized (this) {
            this.increment();
            System.out.println("giá trị sau khi tăng: " + Thread.currentThread().getName() + " " + this.getCount());
            this.decrement();
            System.out.println("giá trị cuối cùng: " + Thread.currentThread().getName() + " " + this.getCount());
        }


    }
}

public class RaceConditionDemo {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread(counter, "Thread-1");
        Thread t2 = new Thread(counter, "Thread-2");
        Thread t3 = new Thread(counter, "Thread-3");
        t1.start();
        t2.start();
        t3.start();
    }
}
