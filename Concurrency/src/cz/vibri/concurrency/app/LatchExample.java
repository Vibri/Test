package cz.vibri.concurrency.app;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LatchWorker implements Runnable {

    private int id;
    private CountDownLatch latch;

    public LatchWorker(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();
    }

    private void doWork() {
        try {
            System.out.println("Thread with ID " + this.id + " starts working...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class LatchExample {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);

        ExecutorService service = Executors.newSingleThreadExecutor();

        for (int i=0; i<5; i++) {
            service.execute(new LatchWorker(i, latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("All tasks have been finished...");
        service.shutdown();
    }
}
