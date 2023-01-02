package com.aaronr92.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool();

        Connection connection = Connection.getInstance();

        for (int i = 0; i < 200; i++) {
            executor.submit(connection::connect);
        }

        executor.shutdown();

        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}
