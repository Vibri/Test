package cz.vibri.concurrency.app;

class Work implements Runnable {

    @Override
    public void run() {
        for (int i=0; i<10;i++) {
            System.out.println(i);
        }
    }
}


public class PriorityExample {
    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getName());
//        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
//        System.out.println(Thread.currentThread().getPriority());

        Thread t1 = new Thread(new Work());
        t1.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        System.out.println("this is main thread");
    }
}
