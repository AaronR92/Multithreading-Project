package com.aaronr92.lowLevelSyncExample;

import java.util.LinkedList;
import java.util.Random;

class Processor {
    private final Random random = new Random();
    private final LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private final Object lock = new Object();
    private int value = 0;
    public void produce() throws InterruptedException {
        synchronized (lock) {
            checkLimit();
            list.add(value++);
            lock.notify();
        }

        Thread.sleep(random.nextInt(1000));
    }
    public void consume() throws InterruptedException {
        synchronized (lock) {
            checkEmpty();
            System.out.printf("List size is %d", list.size());
            Integer value = list.removeFirst();
            System.out.printf("; value is %d%n", value);
            lock.notify();
        }

        Thread.sleep(random.nextInt(1000));
    }

    private void checkLimit() throws InterruptedException {
        while (list.size() == LIMIT) {
            System.out.println("Limit!");
            lock.wait();
        }
    }

    private void checkEmpty() throws InterruptedException {
        while (list.size() == 0) {
            System.out.println("Empty!");
            lock.wait();
        }
    }
}
