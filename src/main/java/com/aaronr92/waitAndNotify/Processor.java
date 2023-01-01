package com.aaronr92.waitAndNotify;

import java.util.Scanner;

public class Processor {
    private Scanner scanner = new Scanner(System.in);
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.printf("%s is running...%n",
                    Thread.currentThread().getName());
            wait();
            System.out.printf("%s resumed%n",
                    Thread.currentThread().getName());
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Waiting for return key...");
            scanner.nextLine();
            notify();
            System.out.println("Thread notified");
        }
    }
}
