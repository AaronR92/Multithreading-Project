package com.aaronr92.lowLevelSyncExample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    private static final Processor processor = new Processor();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService producers = Executors.newFixedThreadPool(2);
        ExecutorService consumers = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 100; i++) {
            producers.submit(new ProducerRunnable());
            consumers.submit(new ConsumerRunnable());
        }

        producers.shutdown();
        consumers.shutdown();
    }

    private static class ProducerRunnable implements Runnable {
        @Override
        public void run() {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static class ConsumerRunnable implements Runnable {
        @Override
        public void run() {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
