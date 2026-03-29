package cz.vibri.concurrency.app;

import cz.vibri.concurrency.runners.Run1;
import cz.vibri.concurrency.runners.Run2;

public class App2 {

	public static void main(String[] args) throws InterruptedException {

		// It is not parallel
		Thread t1 = new Thread(new Run1());
		Thread t2 = new Thread(new Run2());

		long globalStart = System.currentTimeMillis();
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		long globalEnd = System.currentTimeMillis();

		System.out.println("Total time: " + (globalEnd - globalStart) + " ms");
	}

}
