package cz.vibri.udemy.streams;

import java.io.FileOutputStream;

public class OutStream {

	public static void main(String[] args) {
		try {
			FileOutputStream fileOutput = new FileOutputStream("example2.txt", true);

			char H = 'H';

			fileOutput.write(H);

			String str = "Hello OutputStreams!";

			fileOutput.write(str.getBytes());

			fileOutput.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
