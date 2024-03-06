// @C. Prickartz

package org.pearharmony.data.messages;

import org.pearharmony.data.MsgTrans;
import org.pearharmony.network.Decoder;
import org.pearharmony.network.Encoder;

public class TextMessage extends DefaultMessage {
    public TextMessage(String addresse, String data) {
        super(addresse,data);
    }

    @Override
    public void send() {
        Encoder enco = new Encoder();
        netCtrl.send2Peer(address,10000,enco.text(data));
    }

    @Override
    public void recive() {
        MsgTrans msgTrans = new MsgTrans();
        int transMsgResult;
        do {
            transMsgResult = msgTrans.setMsg(address + ": " + data);
        } while (transMsgResult != 0);
    }
}
