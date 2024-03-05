// @C. Prickartz

package org.pearharmony.data;

public class MsgTrans {

    // startup init massage and var for Latest incoming massage
    private static String msg = "#: initialized successfully! \n" + "#: now running!";

    public void setMsg(String _msg) {
        msg = _msg;
    } // set new incoming msg

    public String getMsg() {
        return msg;
    }

    public void delMsg(String _msg) { // deleted msg after read verification
        if (_msg.matches(msg)) {
            msg = "";
        }
    }
}
