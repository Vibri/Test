package cz.vibri.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class PrintIntApp {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        int[] numbers = createArray(30);

        PrintInt action = new PrintInt(numbers, 0, numbers.length -1);
        action.invoke();
    }


    private static int[] createArray(int n) {

        int[] a = new int[n];
        for (int i=0; i<n;++i) {
            a[i] = i;
        }
        return a;
    }
}
