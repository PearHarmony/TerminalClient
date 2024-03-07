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

    // create msg with address(recipient or sender) and data(String of text or path to file)
    public DefaultMessage(String address, String data) {
        this.address = address;
        this.data = data;

        netCtrl = new NetworkControler();
    }

    public abstract void send();

    public abstract void receive();

    // check if recipient is in address book -> replace name when found
    protected String checkIfAddressIsInContacts(String recipient) {
        Contacts addressBook = new Contacts();
        String address = addressBook.getContactIP(recipient.toLowerCase());

        if (address.equals("#")) {
            return recipient.toLowerCase();
        } else {
            return address.toLowerCase();
        }
    }

    // check if sender is in address book -> replace ip when found
    protected String checkIfNameIsInContacts(String sender) {
        Contacts addressBook = new Contacts();
        String name = addressBook.getContactName(sender.toLowerCase());

        if (name.equals("#")) {
            return sender.toLowerCase();
        } else {
            return name.toLowerCase();
        }
    }
}
