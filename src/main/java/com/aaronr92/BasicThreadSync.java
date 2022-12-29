package com.aaronr92;

import java.util.Scanner;

public class BasicThreadSync {
    /**
     * In some cases method run() is storing local value of a running variable, so it cannot be stopped,
     * to solve this problem we should use volatile keyword
     * About: https://www.baeldung.com/java-volatile
     */
    public volatile static boolean running;

    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        running = true;
    }
}

class Processor extends Thread {

    @Override
    public void run() {
        while (!BasicThreadSync.running) {
            System.out.println("Hello!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}