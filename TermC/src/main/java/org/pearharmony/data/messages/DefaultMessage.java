// @C. Prickartz

package org.pearharmony.data.messages;

import org.pearharmony.data.MsgTrans;
import org.pearharmony.network.Encoder;
import org.pearharmony.network.NetworkControler;

public abstract class DefaultMessage {
    protected String address;
    protected String data;
    protected MsgTrans msgTrans;
    protected NetworkControler netCtrl;
    protected Encoder enco;

    public DefaultMessage(String addresse, String data) {
        this.address = addresse;
        this.data = data;

        netCtrl = new NetworkControler();
    }

    public abstract void send();

    public abstract void recive();
}
