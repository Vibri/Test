package cz.vibri.udemy.streams;

import java.io.PrintStream;

public class PrtStream {

	public static void main(String[] args) {
		try {
			PrintStream out = new PrintStream("example8.txt");

			int var1 = 10;

			System.out.println("The value of var1 is: " + var1);

			out.println("The value of var1 is: " + var1);
			out.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
