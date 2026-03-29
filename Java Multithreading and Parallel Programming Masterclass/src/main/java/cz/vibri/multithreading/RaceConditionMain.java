package cz.vibri.multithreading;

import java.util.ArrayList;
import java.util.List;

public class RaceConditionMain {

    private static int globalCounter = 0;

    public static  void main(String[] args) {
        List<Thread> thrreads = new ArrayList<>();

        ThreadGroup group = new ThreadGroup("Group1");

        for (int i = 1; i<=10; i++) {
            Thread t = new Thread(group, new MyThread());
            t.start();
            thrreads.add(t);
        }

        group.interrupt();

        thrreads.forEach(t-> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Total = " + globalCounter);
    }

    static class MyThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(99999);
            } catch (InterruptedException e) {
            }
            globalCounter++;

//        int localCounter = globalCounter;
//        localCounter = localCounter + 1;
//        globalCounter = localCounter;
        }
    }
}
