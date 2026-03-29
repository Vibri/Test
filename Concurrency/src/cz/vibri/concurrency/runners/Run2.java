package cz.vibri.concurrency.runners;

public class Run2 implements Runnable {
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		for (int i=0; i<100; i++) {
			System.out.println("Runner2: " + i);
		}
		long end = System.currentTimeMillis();
		System.out.println("Run2 finished in " + (end - start) + " ms");
	}
}
