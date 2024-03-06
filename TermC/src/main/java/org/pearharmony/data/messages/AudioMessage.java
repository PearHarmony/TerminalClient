// @C. Prickartz

package org.pearharmony.data.messages;

import org.pearharmony.data.MsgTrans;
import org.pearharmony.network.Encoder;

import java.nio.file.Paths;

public class AudioMessage extends DefaultMessage {
    public AudioMessage(String addresse,String data) {
        super(addresse,data);
    }

    @Override
    public void send() {
        Encoder enco = new Encoder();
        netCtrl.send2Peer(address,10000,enco.sound(Paths.get(data)));
    }

    @Override
    public void recive() {
        MsgTrans msgTrans = new MsgTrans();
        int transMsgResult;
        do {
            transMsgResult = msgTrans.setMsg(address + ": You received a new audio file: " + data);
        } while (transMsgResult != 0);
    }
}
