package cz.vibri.concurrency.forkjoin;

import java.util.concurrent.RecursiveAction;

public class PrintInt extends RecursiveAction {

    private int[] ints;
    private int lowerIndex;
    private int upperIndex;

    public PrintInt(int[] ints, int lowerIndex, int upperIndex) {
        this.ints = ints;
        this.lowerIndex = lowerIndex;
        this.upperIndex = upperIndex;
    }

    @Override
    protected void compute() {
        if (upperIndex - lowerIndex > 2) {
            System.out.println("Parallel print int" + lowerIndex + " " + upperIndex);
            int middle = (lowerIndex + upperIndex)/2;
            PrintInt action1 = new PrintInt(ints, lowerIndex, middle);
            PrintInt action2 = new PrintInt(ints, middle + 1, upperIndex);

            action1.fork();
            action2.fork();

            action1.join();
            action2.join();
        } else {
            for (int i=lowerIndex; i <= upperIndex; i++) {
                System.out.println(ints[i]);
            }
        }
    }
}
