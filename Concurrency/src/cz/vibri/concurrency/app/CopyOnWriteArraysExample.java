package cz.vibri.concurrency.app;

public class CopyOnWriteArraysExample {
    public static void main(String[] args) {
        ConcurrentArray concurrentArray = new ConcurrentArray();
        concurrentArray.simulate();
    }
}
