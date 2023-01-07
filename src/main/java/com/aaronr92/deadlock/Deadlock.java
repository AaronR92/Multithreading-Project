package com.aaronr92.deadlock;

public class Deadlock {
    public static void main(String[] args) throws InterruptedException {
        final Runner runner = new Runner();
        final Thread t1 = new Thread(() -> {
            try {
                runner.firstThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        final Thread t2 = new Thread(() -> {
            try {
                runner.secondThread();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        runner.finished();
    }
}
