package com.basicsstrong.functional.practice;

import java.util.function.Function;

public class ConstructorReference {
    public static void main(String[] args) {
        Function<Runnable, Thread> threadGenerator = r -> new Thread(r);
        Function<Runnable, Thread> threadGenerator2 = Thread :: new;

        Runnable task1 = () -> System.out.println("Task 1 executed");
        Runnable task2 = () -> System.out.println("Task 2 executed");

        Thread t1 = threadGenerator2.apply(task1);
        Thread t2 = threadGenerator2.apply(task2);

        t1.start();
        t2.start();
        threadGenerator2.
                apply(() -> System.out.println("Task 3 executed")).
                start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
