package com.aaronr92.interruptingThreads;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InterruptingThreads {

    private static final Random r = new Random();

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Staring.");

        ExecutorService executor = Executors.newCachedThreadPool();

        Future<?> future = executor.submit(() -> {
            for (int i = 0; i < 1E8; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("We've been interrupted");
                    break;
                }
                Math.sin(r.nextDouble());
            }

            return null;
        });

        executor.shutdown();

        Thread.sleep(100);

        // Similar
//        future.cancel(true);
        executor.shutdownNow();

        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Finished!");
    }
}
