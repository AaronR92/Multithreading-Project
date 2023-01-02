package com.aaronr92.reEntrantLocks;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();

    private void increment() throws InterruptedException {
        for (int i = 0; i < 1_000; i++) {
            count++;
            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();    // disadvantage - when code inside will throw
                        // an exception, unlock() will never be called
        System.out.println("Waiting...");
        cond.await();   // executing wait method and releasing the lock
        System.out.println("Waking up...");

        try {
            increment();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " unlocked");
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        lock.lock();
        Thread.sleep(1000);

        System.out.println("Press return key to continue...");
        new Scanner(System.in).nextLine();
        System.out.println("Got return!");

        cond.signal();

        try {
            increment();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " unlocked");
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is " + count);
    }
}
