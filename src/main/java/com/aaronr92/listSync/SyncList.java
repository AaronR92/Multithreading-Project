package com.aaronr92.listSync;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Does the same thing as SyncCodeBlocks.java class
 */
public class SyncList {
    private static List<Integer> l1 = Collections.synchronizedList(new ArrayList<>());
    private static List<Integer> l2 = Collections.synchronizedList(new ArrayList<>());
    private static Random random = new Random();

    private static void operationOne() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        l1.add(random.nextInt(1000));
    }
    private static void operationTwo() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        l2.add(random.nextInt(1000));
    }
    private static void process() {
        for (int i = 0; i < 1000; i++) {
            operationOne();
            operationTwo();
        }
    }

    public static void main(String[] args) {
        Thread thread1 = new ProcessThread();
        Thread thread2 = new ProcessThread();

        long startTime = System.currentTimeMillis();
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long elapsedTime = System.currentTimeMillis() - startTime;

        System.out.printf("Execution time: %d\n", elapsedTime);
        System.out.printf("List 1: %d; List2: %d\n", l1.size(), l2.size());
    }

    static class ProcessThread extends Thread {
        @Override
        public void run() {
            process();
        }
    }
}


