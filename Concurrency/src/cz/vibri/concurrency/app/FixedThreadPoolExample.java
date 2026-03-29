package cz.vibri.concurrency.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class HardWork implements Runnable {
    private int id;

    public HardWork(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id " + id + " is in work - thread id: " + Thread.currentThread().getName() + " thread id:" + Thread.currentThread().getId());
        long duration = Double.valueOf(Math.random()*5).longValue() ;
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Task with id " + id  + " finished." + " thread id:" + Thread.currentThread().getId());

        // because we have to shut down the executor!!!!
    }
}

public class FixedThreadPoolExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i<10; i++) {
            executor.execute(new HardWork(i));
        }
    }
}
