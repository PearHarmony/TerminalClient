// @Veljko
// edit: c. prickartz

package org.pearharmony.network;

// A Java program for a Client

import org.pearharmony.data.messages.TextMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
			TextMessage msg = new TextMessage(address,"Address not found!");
			msg.receive();
			return;
		}

		try {
			out.write(data);
			out.close();
			socket.close();
		} catch (IOException e) {
			TextMessage msg = new TextMessage(address,"Connection refused!");
			msg.receive();
		}
	}
}
