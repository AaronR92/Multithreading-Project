package com.aaronr92.reEntrantLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    private void increment() {
        for (int i = 0; i < 10_000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        lock.lock();    // disadvantage - when code inside will throw
                        // an exception, unlock() will never be called
        try {
            increment();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void secondThread() throws InterruptedException {
        lock.lock();
        try {
            increment();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void finished() {
        System.out.println("Count is " + count);
    }
}
