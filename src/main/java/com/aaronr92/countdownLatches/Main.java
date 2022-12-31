package com.aaronr92.countdownLatches;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        // number is the count of threads to countdown to
        CountDownLatch latch = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            executor.submit(new Processor(latch));
        }

        latch.await();
        System.out.println("All tasks finished");
        executor.shutdown();
    }
}
