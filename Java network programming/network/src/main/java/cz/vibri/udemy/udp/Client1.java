package cz.vibri.udemy.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client1 {

	public static void main(String[] args) throws IOException {

		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];

		boolean stop = false;

		while (!stop) {
			String stringSendData = "Client 1 text message";
			sendData = stringSendData.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9090);
			clientSocket.send(sendPacket);

		}
		clientSocket.close();
	}

}
