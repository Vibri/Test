package cz.vibri.udemy.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {

	public static void main(String[] args) {

		try {
			DatagramSocket clientSocket = new DatagramSocket(0);
			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];

			InetAddress serverAddress = InetAddress.getByName("localhost");

			String stringSendData = "Hello Server";
			sendData = stringSendData.getBytes();

			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9090);
			clientSocket.send(sendPacket);

			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			clientSocket.receive(receivePacket);
			receiveData = receivePacket.getData();

			String stringReceiveData = new String(receiveData);
			System.out.println("FROM SERVER: " + stringReceiveData);
			clientSocket.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
