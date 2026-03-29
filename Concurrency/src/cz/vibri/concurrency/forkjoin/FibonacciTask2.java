package cz.vibri.concurrency.forkjoin;

import java.util.concurrent.RecursiveTask;

public class FibonacciTask2 extends RecursiveTask<Integer> {

    private int n;

    public FibonacciTask2(int n){
        this.n = n;
    }

    @Override
    protected Integer compute() {

        // F(0) = F(1) = 0
        if(n <= 1)
            return n;

        FibonacciTask2 fib1 = new FibonacciTask2(n-1);
        FibonacciTask2 fib2 = new FibonacciTask2(n-2);

//        fib1.fork();
        fib2.fork();

        // the actual thread executes the fib1
        // and we create anotheer thread (insert it into the pool )
        // associated with fib2
        return fib1.compute()+ fib2.join();
    }
}

