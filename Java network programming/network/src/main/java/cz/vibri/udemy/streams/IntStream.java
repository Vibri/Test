package cz.vibri.udemy.streams;

import java.io.FileInputStream;

public class IntStream {

	public static void main(String[] args) {
		try {
			FileInputStream inputStream = new FileInputStream("example1.txt");

			int data = inputStream.read();
			while (data != -1) {
				System.out.println((char) data);
				data = inputStream.read();
			}

			inputStream.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
