// @Veljko
package org.pearharmony.network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import org.pearharmony.data.messages.ImgMessage;
import org.pearharmony.data.messages.AudioMessage;
import org.pearharmony.data.messages.TextMessage;

public class Handler implements Runnable {
    private Socket socket;
    private DataInputStream in;
    private Decoder de = new Decoder();

    byte[] dog;

    public Handler(Socket _socket) {
        socket = _socket;
    }

    public String getIP() {
        String string = socket.getInetAddress() + "";
        return string.replace("/", "");
    }

    public void run() {
        // takes input from the client socket
        try {
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            dog = in.readAllBytes();
            switch (de.getType(dog)) {
                default:
                    TextMessage t2msg = new TextMessage(getIP(),de.text(de.cleanData(dog)));
                    t2msg.receive();
                break;
                case 0x00:
                    TextMessage tmsg = new TextMessage(getIP(),de.text(de.cleanData(dog)));
                    tmsg.receive();
                    break;
                case 0x01:
                    ImgMessage Imsg = new ImgMessage(getIP(),de.picture(de.cleanData(dog),System.getProperty("user.dir")).toString());
                    Imsg.receive();
                    break;
                case 0x02:
                    AudioMessage auMsg = new AudioMessage(getIP(),de.sound(de.cleanData(dog),System.getProperty("user.dir")).toString());
                    auMsg.receive();
                    break;
            }
            // close connection
            socket.close();
            in.close();
        } catch (IOException e) {
            System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
        }
    }

}
