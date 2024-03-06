// @C. Prickartz

package org.pearharmony.data.messages;

import org.pearharmony.data.Contacts;
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

    public abstract void receive();

    protected String checkIfAddressIsInContacts(String recipient) {
        Contacts addressBook = new Contacts();
        String address = addressBook.getContactIP(recipient);

        if (address.equals("#")) {
            return recipient;
        } else {
            return address;
        }
    }

    protected String checkIfNameIsInContacts(String sender) {
        Contacts addressBook = new Contacts();
        String name = addressBook.getContactName(sender);

        if (name.equals("#")) {
            return sender;
        } else {
            return name;
        }
    }
}
