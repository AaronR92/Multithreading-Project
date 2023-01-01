package com.aaronr92.waitAndNotify;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();

        Thread producer = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println(producer.getName());
        System.out.println(consumer.getName());

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
