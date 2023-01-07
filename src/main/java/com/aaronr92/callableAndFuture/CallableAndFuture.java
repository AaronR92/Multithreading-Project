package com.aaronr92.callableAndFuture;

import java.util.Random;
import java.util.concurrent.*;

public class CallableAndFuture {

    private static final Random random = new Random();
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<Integer> future = executor.submit(() -> {
            System.out.println("Starting...");
            int duration = random.nextInt(4000);

            if (duration > 2000) {
                throw new IllegalArgumentException("Waiting for too long");
            }

            Thread.sleep(duration);

            System.out.println("Finished!");
            return duration;
        });

        System.out.println("Result is " + future.get());

        executor.shutdown();
    }
}
