package com.aaronr92.threadPools;

import java.time.LocalDateTime;
import java.util.Date;

class Processor implements Runnable {
    private int id;
    Processor(int id) {
        this.id = id;
        System.out.printf("[%d] Created%n", id);
    }
    @Override
    public void run() {
        System.out.printf("[%d] Starting at %s%n", id, LocalDateTime.now());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("[%d] Completed at %s%n", id, LocalDateTime.now());
    }

    public int getId() {
        return id;
    }
}
