package cz.vibri.multithreading.projects.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerMain {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        Thread t = new Thread(() -> {
            try {
                String receivedValue = exchanger.exchange("value1");
                System.out.println("ReceivedValue: " + receivedValue + " in thread " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t.start();

        String receivedValue = exchanger.exchange("value2");
        System.out.println("ReceivedValue: " + receivedValue + " in thread " + Thread.currentThread().getName());

    }
}
