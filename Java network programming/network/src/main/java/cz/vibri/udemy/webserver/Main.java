package cz.vibri.udemy.webserver;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			ServerSocket serverSocket = new ServerSocket(80); // create a server socket object
			boolean isStop = false;

			while (!isStop) // while server is not stopped
			{
				Socket clientSocket = serverSocket.accept(); // accept a client
				System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " is connected"); // print
																													// client
																													// ip
																													// address
				ClientThread clientThread = new ClientThread(clientSocket); // create a new thread for each client
				clientThread.start(); // start the thread
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

}
