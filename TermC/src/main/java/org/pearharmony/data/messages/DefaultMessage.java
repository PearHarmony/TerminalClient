package org.pearharmony.data.messages;

import org.pearharmony.data.MsgTrans;

public abstract class DefaultMessage {
    protected String address;
    protected MsgTrans msgTrans;

    public DefaultMessage(String addresse) {
        this.address = addresse;
        MsgTrans msgTrans = new MsgTrans();
    }

    public abstract void Send();

    public abstract void Recive();
}
