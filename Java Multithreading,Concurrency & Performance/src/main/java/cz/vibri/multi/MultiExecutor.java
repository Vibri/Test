package cz.vibri.multi;

import java.util.Arrays;
import java.util.List;

public class MultiExecutor {

    // Add any necessary member variables here
    final List<Runnable> tasks;

    public static void main(String[] args) {
        List<Runnable> list = Arrays.asList(new Runnable() {
            @Override
            public void run() {
                System.out.println("JJ");
            }
        }, new Runnable() {
            @Override
            public void run() {
                System.out.println("KK");
            }
        });
        new MultiExecutor(list).executeAll();
    }

    /*
     * @param tasks to executed concurrently
     */
    public MultiExecutor(List<Runnable> tasks) {
        // Complete your code here
        this.tasks = tasks;
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        // complete your code here
        for (int i = 0; i < tasks.size();i++) {
            new Thread(tasks.get(i)).start();
        }
    }
}