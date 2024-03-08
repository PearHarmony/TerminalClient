// @Veljko
package org.pearharmony.network;

public class NetworkControler {
    //Control control;

    public NetworkControler() {

    }

    public void send2Peer(String _ip, int _port, byte[] _data) {
        //method to send data to peer
        Sender client = new Sender(_ip, _port, _data);
        Thread sender = new Thread(client);
        sender.start();
    }

    public void startListening(int _port) {
        //method to start listening for incoming messages
        Listener host = new Listener(_port);
        Thread listener = new Thread(host);
        listener.start();
    }
}
