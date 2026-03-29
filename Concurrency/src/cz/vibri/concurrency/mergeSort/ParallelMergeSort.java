package cz.vibri.concurrency.mergeSort;

public class ParallelMergeSort {

    private int[] nums;
    //merge sort is not an in-place algorithm
    private int[] tempArray;

    public ParallelMergeSort(int[] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    public void parallelMergeSort(int low, int high, int numOfThreads) {
        if(numOfThreads <= 1) {
            mergeSort(low, high);
            return;
        }

        int middleIndex = (low+high)/2;

        Thread leftSorter = createThread(low, middleIndex, numOfThreads);
        Thread rightSorter = createThread(middleIndex + 1, high, numOfThreads);

        leftSorter.start();
        rightSorter.start();

        try {
            leftSorter.join();
            rightSorter.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        merge(low, middleIndex, high);

    }

    private Thread createThread(int low, int high, int numOfThreads) {
        return new Thread() {
            @Override
            public void run() {
                parallelMergeSort(low, high, numOfThreads/2);
            }
        };
     }

    public void sort() {
        mergeSort(0, nums.length-1);
    }

    // DIVIDE and CONQUER approach
    private void mergeSort(int low, int high) {
        // base case
        if (low >= high)
            return;
        // middle item
        int middleIndex = (low+high)/2;

        // recursively on both sub-arrays
        // we keep splitting the problem into smaller and smaller sub-problems
        // until a given array contains only one item
        mergeSort(low, middleIndex);
        mergeSort(middleIndex+1,high);

        // we have to combine the sub-solution
        merge(low, middleIndex, high);
    }

    private void merge(int low, int middleIndex, int high) {
        // copy the items into the temporary array
        for (int i=low; i<=high; i++) {
            tempArray[i] = nums[i];
        }
        int i = low;
        int j = middleIndex + 1;
        int k = low;

        // we consider the temp array and copy the items into the nums
        while(i<=middleIndex && j<=high) {
            if (tempArray[i] < tempArray[j]) {
                nums[k] = tempArray[i];
                i++;
            } else {
                nums[k] = tempArray[j];
                j++;
            }
            ++k;
        }
        // we have to copy the items from the left subarray (if there are any)
        while (i<=middleIndex) {
            nums[k] = tempArray[i];
            ++k;
            ++i;
        }
        // we have to copy the items from the right subarray (if there are any)
        while (j<=middleIndex) {
            nums[k] = tempArray[j];
            ++k;
            ++j;
        }
    }

    public void showArray() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]+ " ");
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
