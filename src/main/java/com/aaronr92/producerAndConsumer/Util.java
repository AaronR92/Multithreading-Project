package com.aaronr92.producerAndConsumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Util {
    private static final BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
    private static final Random random = new Random();

    private static void producer() throws InterruptedException {
        while (true) {
            Thread.sleep(100);
            blockingQueue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        while (true) {
            Thread.sleep(200);

            if (random.nextInt(10) == 0) {
                Integer value = blockingQueue.take();

                System.out.printf("Taken value: %d; Queue size is %d%n",
                        value, blockingQueue.size());
            }
        }
    }

    public static class Producer extends Thread {
        @Override
        public void run() {
            try {
                producer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class Consumer extends Thread {
        @Override
        public void run() {
            try {
                consumer();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class QueueGetter extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println(blockingQueue);
                    Thread.sleep(2000L);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
