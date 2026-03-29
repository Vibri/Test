package cz.vibri.multithreading;

public class ThreadLocalMain {

    private static int globalConter = 0;

    private static java.lang.ThreadLocal<String> threadLocal = new java.lang.ThreadLocal<>();
    private static java.lang.ThreadLocal<String> threadLocal2 = java.lang.ThreadLocal.withInitial(()->"initialValue");

    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread(), "Thread1");
        Thread t2 = new Thread(new MyThread(), "Thread2");

        t1.start();
        t2.start();
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            int counter = 0;

            threadLocal.set("myValue: " + Thread.currentThread().getName());
            System.out.println(threadLocal.get());
        }
    }
}
