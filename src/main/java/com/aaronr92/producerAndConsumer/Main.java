package com.aaronr92.producerAndConsumer;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread producer1 = new Util.Producer();
        Thread consumer1 = new Util.Consumer();
        Thread getter = new Util.QueueGetter();

        producer1.start();
        consumer1.start();
        getter.start();

        producer1.join();
        consumer1.join();
        getter.join();
    }
}
