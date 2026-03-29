package cz.vibri.concurrency.app;

import java.util.concurrent.Exchanger;

class FirstExchangerThread implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;

    public FirstExchangerThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("FirstThread incremented the counter: " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("FirstThread get the counter: " + counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

class SecondExchangerThread implements Runnable {

    private int counter;
    private Exchanger<Integer> exchanger;

    public SecondExchangerThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            counter--;
            System.out.println("SecondThread decremented the counter: " + counter);

            try {
                counter = exchanger.exchange(counter);
                System.out.println("SecondThread get the counter: " + counter);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

public class ExchangerExample {
    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        FirstExchangerThread first = new FirstExchangerThread(exchanger);
        SecondExchangerThread second = new SecondExchangerThread(exchanger);

        new Thread(first).start();
        new Thread(second).start();

    }
}
