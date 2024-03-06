// @C. Prickartz

package org.pearharmony.data.messages;

import org.pearharmony.data.MsgTrans;
import org.pearharmony.network.Encoder;

import java.nio.file.Paths;

public class ImgMessage extends DefaultMessage {

    public ImgMessage(String addresse, String data) {
        super(addresse,data);
    }

    @Override
    public void send() { // send message
        Encoder enco = new Encoder();
        netCtrl.send2Peer(checkIfAddressIsInContacts(address),10000,enco.picture(Paths.get(data)));
    }

    @Override
    public void receive() { // receive new message
        MsgTrans msgTrans = new MsgTrans();
        int transMsgResult;
        do { // try sending message to ui -> if fail try aging
            transMsgResult = msgTrans.setMsg(checkIfNameIsInContacts(address) + ": You received a new image: " + data);
        } while (transMsgResult != 0);
    }
}
