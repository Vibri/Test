package cz.vibri.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class App {

    /*
     * fork() - asynchronously executes the given tasks in the pool
     *           We can call it when using RecursiveTask<> or RecursiveAction
     *
     *  join() - returns the result of the computation when it is finished
     *  invoke() - executes the given task + wait + return the result upon completion
     *
     */

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        SimpleRecursiveAction action = new SimpleRecursiveAction(800);
        action.invoke();
    }
}
