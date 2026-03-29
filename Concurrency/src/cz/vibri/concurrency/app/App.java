package cz.vibri.concurrency.app;

import cz.vibri.concurrency.runners.Runner1;
import cz.vibri.concurrency.runners.Runner2;

public class App {

	public static void main(String[] args) {
		Runner1 t1 = new Runner1();
		Runner2 t2 = new Runner2();
		
		t1.start();
		t2.start();
	}
	
}
