package cz.vibri.concurrency.runners;

public class Run1 implements Runnable {
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		for (int i=0; i<100; i++) {
			System.out.println("Runner1: " + i);
		}
		long end = System.currentTimeMillis();
		System.out.println("Run1 finished in " + (end - start) + " ms");
	}
}
