package cz.vibri.concurrency.runners;

public class Runner1 extends Thread {
	@Override
	public void run() {
		for (int i=0; i<100; i++) {
			System.out.println("Runner1: " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
