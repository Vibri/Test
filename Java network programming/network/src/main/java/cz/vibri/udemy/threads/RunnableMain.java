package cz.vibri.udemy.threads;

public class RunnableMain {

	public static void main(String[] args) {
		ImplementRunnable runnable1 = new ImplementRunnable(1);
		Thread thread1 = new Thread(runnable1);
		thread1.start();
		ImplementRunnable runnable2 = new ImplementRunnable(2);
		Thread thread2 = new Thread(runnable2, "Server2");
		System.out.println(thread2.getName());
		thread2.start();

		ImplementRunnable runnable3 = new ImplementRunnable(3);
		runnable3.start();
	}

}
