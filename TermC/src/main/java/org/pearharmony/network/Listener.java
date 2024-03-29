// @Veljko
// edit: C. Prickartz

package org.pearharmony.network;

// A Java program for a Server

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener implements Runnable {
    private ServerSocket server;
	private int port;
	public Handler handle;
	Thread thread;

	public Listener(int _port) {
		port = _port;
	}

	// constructor with port
	@SuppressWarnings("InfiniteLoopStatement")
	public void run() {

		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException("The port PearHarmony is trying to use is already being used by another program!");
		}
		while (true) {

			// starts server and waits for a connection with a port
			try {

                // initialize socket and input stream
                Socket socket = server.accept();
				//handle connection, creates new thread with handler instance
				handle = new Handler(socket);
				thread = new Thread(handle);
				thread.start();

			} catch (IOException e) {
				System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
			}
		}

	}

}
