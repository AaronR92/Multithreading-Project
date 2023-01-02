package com.aaronr92.semaphores;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Connection {
    private static final Connection instance = new Connection();
    private final Semaphore semaphore = new Semaphore(10, true);
    private final Random random = new Random();
    private int connections;

    private Connection() {
        connections = 0;
    }

    @SneakyThrows
    public void connect() {
        semaphore.acquire();

        try {
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect() {
        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            Thread.sleep(random.nextInt(100) + 400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }
    }

    public static Connection getInstance() {
        return instance;
    }
}
