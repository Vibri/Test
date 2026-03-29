package cz.vibri.udemy.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	public static void main(String[] args) throws IOException {

		DatagramSocket serverSocket = new DatagramSocket(9090);

		byte[] receiveData = new byte[1024];

		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);
		}
	}

}
