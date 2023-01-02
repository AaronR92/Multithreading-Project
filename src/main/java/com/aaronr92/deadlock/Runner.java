package com.aaronr92.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Runner {
    private final Account acc1 = new Account(10000);
    private final Account acc2 = new Account(10000);
    private final Random random = new Random();
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public void firstThread() throws InterruptedException {
        for (int i = 0; i < 1_000; i++) {
            acquireLocks(lock1, lock2);

            try {
                Account.transfer(acc1, acc2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        for (int i = 0; i < 1_000; i++) {

            acquireLocks(lock2, lock1);

            try {
                Account.transfer(acc2, acc1, random.nextInt(100));
            } finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Account 1 balance is " + acc1.getBalance());
        System.out.println("Account 2 balance is " + acc2.getBalance());
        System.out.println("Total balance is " +
                (acc1.getBalance() + acc2.getBalance()));
    }

    private void acquireLocks(Lock firstLock, Lock secondLock) {
        while (true) {
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;

            try {
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            } finally {
                if (gotFirstLock && gotSecondLock) {
                    return;
                }

                if (gotSecondLock) {
                    secondLock.unlock();
                }

                if (gotFirstLock) {
                    firstLock.unlock();
                }
            }
        }
    }
}
