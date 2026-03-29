package cz.vibri.udemy.streams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharacterStream2 {

	public static void main(String[] args) {

		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("example5.txt"));
			InputStreamReader in = new InputStreamReader(new FileInputStream("example5.txt"));

			System.out.println("out: " + out.getEncoding());
			System.out.println("in: " + in.getEncoding());

			out.write("reading using InputStreamReader");
			out.flush();
//			out.write("another string");
			out.flush();
			out.close();

			int data = in.read();
			while (data != -1) {
				System.out.print((char) data);
				data = in.read();
			}

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
