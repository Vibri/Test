package cz.vibri.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class FibonacciApp {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new FibonacciTask(25)));
    }
}
