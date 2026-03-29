package cz.vibri.udemy.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {

		try {
			InetAddress serverAddress = InetAddress.getByName("localhost");
			System.out.println("server ip address: " + serverAddress.getHostAddress());
			Socket socket = new Socket(serverAddress, 9090);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(input.readLine());
			out.println("hello server");
			input.close();
			out.close();
			socket.close();

		} catch (UnknownHostException ex1) {
			System.out.println("Unknown host exception: " + ex1.toString());
		} catch (IOException ex) {
			System.out.println("IOException: " + ex.toString());
		} catch (IllegalArgumentException ex) {
			System.out.println("Illegal Argument Exception: " + ex.toString());
		} catch (Exception e) {
			System.out.println("Other exception: " + e.toString());
		}
	}

}
