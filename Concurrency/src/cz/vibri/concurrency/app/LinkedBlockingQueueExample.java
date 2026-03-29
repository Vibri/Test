package cz.vibri.concurrency.app;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueExample {

    public static void main(String[] args) {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(10);

        Runnable producer = () -> {
            try {
                int i = 0;
                while(true) {
                    String s = "Item " + i++;
                    queue.put(s);
                    System.out.println("Produced: " + s);
                    Thread.sleep(500);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };

        Runnable consumer = () -> {
            try {
                while(true) {
                    String s = queue.take();
                    System.out.println("Consumed: " + s);
                    Thread.sleep(3000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        };


        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
