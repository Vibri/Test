package cz.vibri.multithreading.exercise;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Exercise1 {

    private static int finalSum = 0;
    private static Lock lock = new ReentrantLock();
    private static final Exchanger exchanger = new Exchanger();

    public static void main(String[] args) throws InterruptedException {
        exchangeValues();
    }

    public static int exchangeValues() throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        int maxThreads = 100;

        System.out.println("Start");
        for(int i = 0; i < maxThreads; i++) {
            Thread t = new Thread(new WorkerThread(i,exchanger,maxThreads),"Thread-" + i);
            t.start();
            threads.add(t);
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final sum " + finalSum);

        return finalSum;
    }

    static class WorkerThread implements Runnable {

        private final int id;
        private int receivedValuesSum;
        private final Exchanger<Integer> exchanger;
        private final Map<Integer, Integer> map;
        private final int numberOfThreads;

        public WorkerThread(int id, Exchanger<Integer> exchanger, int numberOfThreads) {
            this.id = id;
            this.receivedValuesSum = id;
            this.exchanger = exchanger;
            this.map = new HashMap<>();
            map.put(id, id);
            this.numberOfThreads = numberOfThreads;
        }

        @Override
        public void run() {
            while(map.size() < numberOfThreads) {
                try {
                    Integer receivedInteger = exchanger.exchange(id);
                    if (!map.containsKey(receivedInteger)) {
                        map.put(receivedInteger,receivedInteger);
                        receivedValuesSum += receivedInteger;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println(Thread.currentThread().getName() + " contains sum " + receivedValuesSum);
            lock.lock();
            finalSum += this.receivedValuesSum;
            lock.unlock();

        }
    }
}

