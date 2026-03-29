package cz.vibri.concurrency.app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class ProcessorCallable implements Callable<String> {

    private int id;

    public ProcessorCallable(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "Id: " + id;
    }
}

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        List<Future<String>> list = new ArrayList<>();

        for (int i=0; i<5; i++) {
            Future<String> future = service.submit(new ProcessorCallable(i+1));
            list.add(future);
        }

        for (Future<String> item : list) {
            try {
                System.out.println(item.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }

        service.shutdown();
    }
}
