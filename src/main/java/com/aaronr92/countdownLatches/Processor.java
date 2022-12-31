package com.aaronr92.countdownLatches;

import java.util.concurrent.CountDownLatch;

class Processor implements Runnable {
    private final CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        latch.countDown();
        System.out.println("Finished");
    }
}
