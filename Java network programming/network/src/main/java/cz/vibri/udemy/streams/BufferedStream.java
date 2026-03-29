package cz.vibri.udemy.streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BufferedStream {

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("example6.txt"), 8);
			BufferedWriter writer = new BufferedWriter(new FileWriter("example7.txt"));
			BufferedWriter writer2 = new BufferedWriter(new FileWriter("example7_1.txt"));

			String line = null;

			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
				writer2.write(line);
			}
			writer.close();
			writer2.close();
			reader.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
