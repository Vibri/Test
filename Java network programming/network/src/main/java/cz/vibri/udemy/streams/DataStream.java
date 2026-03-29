package cz.vibri.udemy.streams;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DataStream {

	public static void main(String[] args) {
		File file = new File("example3.txt");

		if (file.exists()) {
			System.out.println("File already exists!" + file.getName());
		} else {
			try {
				if (file.createNewFile()) {
					System.out.println("File was created!");
					System.out.println("File path:" + file.getAbsolutePath());
				} else {
					System.out.println("Cannot create file");
				}
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}

		try {
			DataOutputStream out = new DataOutputStream(new FileOutputStream(file.getName()));
			DataInputStream in = new DataInputStream(new FileInputStream(file.getName()));

			out.writeInt(72);
			out.writeDouble(678.00);
			out.writeFloat(123.45F);

			int var1 = in.readInt();
			double var2 = in.readDouble();
			float var3 = in.readFloat();

			System.out.println("integer value: " + var1);
			System.out.println("double value: " + var2);
			System.out.println("float value: " + var3);

			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
