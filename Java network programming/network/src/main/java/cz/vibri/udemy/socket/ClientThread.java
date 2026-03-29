package cz.vibri.udemy.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

	private Socket clientSocket;

	public ClientThread(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			out.println("Hello client!");
			BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String clientInput = input.readLine();
			System.out.println(clientInput);
			input.close();
			out.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
