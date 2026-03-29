package cz.vibri.concurrency.app;

import cz.vibri.concurrency.runners.Runner1;
import cz.vibri.concurrency.runners.Runner2;

public class JoinApp {

	public static void main(String[] args) {
		Runner1 t1 = new Runner1();
		Runner2 t2 = new Runner2();
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished the tasks ...");
	}

}
