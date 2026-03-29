package cz.vibri.concurrency.sum;

public class ParallelSumWorker extends Thread{

    private int[] nums;
    private int low;
    private int high;
    private int partialSum;

    public ParallelSumWorker(int[] nums, int low, int high) {
        this.nums = nums;
        this.low = low;
        this.high = Math.min(nums.length, high);
    }

    @Override
    public void run() {
        partialSum = 0;
        for (int i = low; i < high; i++) {
            partialSum += nums[i];
        }
    }

    public int getPartialSum() {
        return this.partialSum;
    }
}
