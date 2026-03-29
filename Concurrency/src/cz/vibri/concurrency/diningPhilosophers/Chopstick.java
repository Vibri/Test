package cz.vibri.concurrency.diningPhilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Chopstick {
    private int id;
    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public boolean pickUp(Philosopher philospher, State state) throws InterruptedException {
        // this is where we will simulate deadlock
//        lock.lock(); - this can cause deadlock, better is use tryLock
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philospher + " picked up " + this);
            return true;
        }
        return false;
    }

    public void putDown(Philosopher philosopher, State state) {
        lock.unlock();
        System.out.println(philosopher + "puts down " + state.toString() + " " + this);
    }

    @Override
    public String toString() {
        return "Chopstick{" +
                "id=" + id +
                '}';
    }
}
