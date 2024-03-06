// @Veljko
// edit: c. prickartz

package org.pearharmony.network;

// A Java program for a Client
import java.io.*;
import java.net.*;

public class Sender implements Runnable {
    private String address;
	private int port;
	byte[] data;

	public Sender(String _address, int _port, byte[] _data) {
		address = _address;
		port = _port;
		data = _data;
	}

	public void run() {
		// establish a connection
        // initialize socket and input output streams
        Socket socket;
        DataOutputStream out;
        try {
			socket = new Socket(address, port);
			// sends output to the socket
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
			return;
		}

		try {
			out.write(data);
			out.close();
			socket.close();
		} catch (IOException e) {
			System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
		}
	}
}
