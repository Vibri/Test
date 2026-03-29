package cz.vibri.concurrency.sum;

import java.util.Random;

public class SumApp {
    public static void main(String[] args) {
        Random random = new Random();
        // 100
        // 10000000
        // 100000000
        // 1000000000
        int[] nums = new int[1000000000];

        for (int i=0; i< nums.length; i++) {
            nums[i] = random.nextInt(100);
        }

        SequencialSum sequencialSum = new SequencialSum();

        long start = System.currentTimeMillis();
        System.out.println("Sequencial sum: " + sequencialSum.sum(nums));
        System.out.println("Time: " + (System.currentTimeMillis() - start));

        int n = Runtime.getRuntime().availableProcessors();

        ParallelSum parallelSum = new ParallelSum(n);
        start = System.currentTimeMillis();
        System.out.println("Parallel sum: " + parallelSum.sum(nums));
        System.out.println("Time: " + (System.currentTimeMillis() - start));
    }
}
