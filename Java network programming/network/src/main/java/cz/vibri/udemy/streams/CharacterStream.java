package cz.vibri.udemy.streams;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class CharacterStream {

	public static void main(String[] args) {

		try {
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("example4.txt"), "ASCII");
			InputStreamReader in = new InputStreamReader(new FileInputStream("example4.txt"));

			System.out.println("out: " + out.getEncoding());
			System.out.println("in: " + in.getEncoding());

			out.write("你好");
			out.flush();
//			out.close();
			out.write("another string");
			out.flush();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

}
