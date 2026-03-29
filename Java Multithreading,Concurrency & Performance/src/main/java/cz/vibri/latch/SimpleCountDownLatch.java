package cz.vibri.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCountDownLatch {
    private int count;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public SimpleCountDownLatch(int count) {
        this.count = count;
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */
    public void await() throws InterruptedException {
        /**
         * Fill in your code
         */
        lock.lock();

        try{
           while (count > 0) {
               condition.await();
           }
        }finally {
            lock.unlock();
        }

    }

    /**
     *  Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     *  If the current count already equals zero then nothing happens.
     */
    public void countDown() {
        /**
         * Fill in your code
         */
        lock.lock();
        try {
            if (count != 0) {
                count--;
            }
            if (count == 0) {
                condition.signalAll();
            }

        } finally {
            lock.unlock();
        }
    }

    /**
     * Returns the current count.
     */
    public int getCount() {
        /**
         * Fill in your code
         */
        lock.lock();
        int countNum = 0;
        try{
            countNum = this.count;
        }finally {
            lock.unlock();
        }

        return countNum;
    }

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;
        SimpleCountDownLatch latch = new SimpleCountDownLatch(numberOfThreads);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i<numberOfThreads;i++) {
            threads.add(new Thread(new Runner(latch)));
        }

        threads.forEach(thread -> thread.start());

        latch.await();

        System.out.println("MAIN COMPLETED");

    }

    public static class Runner implements Runnable{
        private final SimpleCountDownLatch latch;

        Runner(SimpleCountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println("Viktor");
            try {
                System.out.println(Thread.currentThread().getName() + " latch count " + latch.getCount());
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " latch count " + latch.getCount());
            System.out.println(Thread.currentThread().getName() + " COMPLETED");
        }
    }
}