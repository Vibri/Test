package cz.vibri.concurrency.app;

class Worker1 implements Runnable{

    // it will be stored in the main memory
    // 1.] variables can be stored on the main memory without the volatile keyword
    // 2.) both of the threads may uses the same cache !!!
    private volatile boolean terminated ;

    @Override
    public void run() {
        while (!terminated) {
            System.out.println("Working class is running...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }
}

public class VolatileKeyword {

    public static void main(String[] args) throws InterruptedException {
        Worker1 worker = new Worker1();
        Thread t1 = new Thread(worker);
        t1.start();

        Thread.sleep(3000);
        worker.setTerminated(true);
        System.out.println("Algorithm is terminated...");
    }
}
