// @Veljko
// edit: C. Prickartz

package org.pearharmony.network;

// A Java program for a Server
import java.net.*;
import java.io.*;

public class Listener implements Runnable {
	// initialize socket and input stream
	private Socket socket;
	private ServerSocket server;
	private int port;
	public Handler handle;
	//private Control control;
	Thread thread;

	public Listener(int _port) {
		port = _port;
	}

	// constructor with port
	public void run() {

		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException("The port PearHarmony is trying to use is already being used by another program!");
		}
		while (true) {

			// starts server and waits for a connection
			try {

				socket = server.accept();
				//handle connection
				handle = new Handler(socket);
				thread = new Thread(handle);
				thread.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
