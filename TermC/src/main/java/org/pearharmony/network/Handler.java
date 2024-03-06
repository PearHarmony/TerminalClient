// @Veljko
package org.pearharmony.network;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import org.pearharmony.data.messages.ImgMessage;
import org.pearharmony.data.messages.AudioMessage;
import org.pearharmony.data.messages.TextMessage;
import org.pearharmony.control.*;

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
                //control.Recive(new TextMessage(getIP(),dog.toString()));
                break;
                case 0x00:
                    TextMessage tmsg = new TextMessage(getIP(),de.text(de.cleanData(dog)));
                    tmsg.Recive();
                    break;
                case 0x01:
                    ImgMessage Imsg = new ImgMessage(getIP(),de.picture(de.cleanData(dog)));
                    break;
                case 0x02:
                    //control.Recive(
                            //new SoundMessage(getIP(), de.sound(de.cleanData(dog), System.getProperty("user.dir"))));
                    break;
            }
            // close connection
            socket.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
