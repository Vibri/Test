package cz.vibri.concurrency.sum;

public class ParallelSum {

    private ParallelSumWorker[] workers;
    private int numOfThreads;

    public ParallelSum(int numOfThreads) {
        this.numOfThreads = numOfThreads;
        this.workers = new ParallelSumWorker[numOfThreads];
    }

    public int sum(int[] nums) {
        int size = (int) Math.ceil(nums.length / 1.0 / numOfThreads);
        for (int i=0;i<numOfThreads;i++) {
            workers[i] = new ParallelSumWorker(nums, i*size, (i+1)*size);
            workers[i].start();
        }

        try {
            for(ParallelSumWorker worker : this.workers)
                worker.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        // we have to sum up the subResults
        int total = 0;
        for (ParallelSumWorker worker : this.workers)   {
            total += worker.getPartialSum();
        }
        return total;
    }
}
