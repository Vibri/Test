package cz.vibri.concurrency;

public class MinMaxMetrics {

    // Add all necessary member variables
    private long min = Long.MAX_VALUE;
    private long max = Long.MIN_VALUE;
    private Object lock = new Object();

    /**
     * Initializes all member variables
     */
    public MinMaxMetrics() {
        // Add code here
    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        // Add code here
        synchronized (lock) {
            if (newSample < min) {
                min = newSample;
            }
            if (newSample > max) {
                max = newSample;
            }
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        // Add code here
        return min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        // Add code here
        return max;
    }
}

