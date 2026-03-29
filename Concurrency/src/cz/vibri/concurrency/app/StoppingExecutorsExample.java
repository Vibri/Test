package cz.vibri.concurrency.app;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class StoppingExecutorsExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i=0; i<100;i++) {
            executor.execute(new HardWork(i+1));
        }

        // we prevent the executor to execute any further tasks
        executor.shutdown();

        // terminate actual (running) tasks
        try {
            if (!executor.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException ex) {
            executor.shutdownNow();
        }
    }
}
