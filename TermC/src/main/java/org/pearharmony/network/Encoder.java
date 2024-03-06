// @Veljko
package org.pearharmony.network;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Encoder {
    public byte[] text(String _string) {
        byte[] data = _string.getBytes(StandardCharsets.UTF_8);
        byte[] pack = new byte[data.length + 1];
        pack[0] = (byte) 0x00;
        for (int i = 0; i < data.length; i++) {
            pack[i + 1] = data[i];
        }
        return pack;
    }

    public byte[] picture(Path _path) {
        try {
            byte[] data = Files.readAllBytes(_path);
            byte[] pack = new byte[data.length + 1];
            pack[0] = (byte) 0x01;
            for (int i = 0; i < data.length; i++) {
                pack[i + 1] = data[i];
            }
            return pack;
        } catch (IOException e) {
            System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
            return null;
        }
    }

    public byte[] sound(Path _path) {
        try {
            byte[] data = Files.readAllBytes(_path);
            byte[] pack = new byte[data.length + 1];
            pack[0] = (byte) 0x02;
            for (int i = 0; i < data.length; i++) {
                pack[i + 1] = data[i];
            }
            return pack;
        } catch (IOException e) {
            System.out.println("Something is dramatically wrong, but you can continue to use the software at your own risk. To fix the error, restart the software");
            return null;
        }
    }
}
