// @C. Prickartz

package org.pearharmony.data.messages;

import org.pearharmony.data.MsgTrans;
import org.pearharmony.network.Encoder;

public class TextMessage extends DefaultMessage {
    public TextMessage(String address, String data) {
        super(address,data);
    }

    @Override
    public void send() { // send the message
        Encoder enco = new Encoder();
        netCtrl.send2Peer(checkIfAddressIsInContacts(address),10000,enco.text(data));
    }

    @Override
    public void receive() { // receive the message
        MsgTrans msgTrans = new MsgTrans();
        int transMsgResult;
        do { // try sending message to ui -> if fail try aging
            transMsgResult = msgTrans.setMsg(checkIfNameIsInContacts(address) + ": " + data);
        } while (transMsgResult != 0);
    }
}
