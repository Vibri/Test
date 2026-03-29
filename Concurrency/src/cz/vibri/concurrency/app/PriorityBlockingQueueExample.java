package cz.vibri.concurrency.app;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class FirstQueueWorker implements Runnable {

//    private BlockingQueue<String> queue;
private BlockingQueue<Person> queue;

//    public FirstQueueWorker(BlockingQueue<String> queue) {
//        this.queue = queue;
//    }
    public FirstQueueWorker(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
//            queue.put("B");
//            queue.put("H");
//            queue.put("F");
            queue.put(new Person(12,"Adam"));
            queue.put(new Person(55,"Kevin"));
            queue.put(new Person(27,"Ana"));
            Thread.sleep(2000);
//            queue.put("A");
            queue.put(new Person(31,"Daniel"));
            Thread.sleep(1000);
//            queue.put("Z");
            queue.put(new Person(15,"Joe"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SecondQueueWorker implements Runnable {

//    private BlockingQueue<String> queue;
//
//    public SecondQueueWorker(BlockingQueue<String> queue) {
//        this.queue = queue;
//    }

    private BlockingQueue<Person> queue;

    public SecondQueueWorker(BlockingQueue<Person> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
            System.out.println(queue.take());
            Thread.sleep(1000);
            System.out.println(queue.take());
            Thread.sleep(2000);
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class PriorityBlockingQueueExample {
    public static void main(String[] args) {
//        BlockingQueue<String> queue = new PriorityBlockingQueue<>();
        BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
         FirstQueueWorker first = new FirstQueueWorker(queue);
         SecondQueueWorker second = new SecondQueueWorker(queue);
         new Thread(first).start();
         new Thread(second).start();
    }
}

class Person implements Comparable<Person> {
    private int age;
    private String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Person person) {
        return name.compareTo(person.getName());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
