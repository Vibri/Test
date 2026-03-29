package cz.vibri.concurrency.app;

public class ProblemsWithMultithreadingExample {

    public static int counter1 = 0;
    public static int counter2 = 0;
    public static int counter3 = 0;

    // we have to make sure this method is executed only by a single thread
    // at a given time
    // because ProblemsWithMultithreadingExample object has a single lock: this is why the methods can not
    // be executed "at the same time" - time slicing algorithm
    public static void increment2() {
        // class level locking
        // rule of thumb: we synchronize blocks that are 100% necessary
        synchronized(ProblemsWithMultithreadingExample.class) {
            counter2++;
        }
    }

    public static synchronized void increment3() {
        counter3++;
    }

    public static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++) {
                    counter1++;
                    increment2();
                    increment3();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++) {
                    counter1++;
                    increment2();
                    increment3();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("The counter is : " + counter1);
        System.out.println("The counter2 is : " + counter2);
        System.out.println("The counter3 is : " + counter3);
    }

    public static void main(String[] args) {
        process();
    }
}
