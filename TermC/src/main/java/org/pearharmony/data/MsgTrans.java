// @C. Prickartz

package org.pearharmony.data;

public class MsgTrans {

    // startup init massage and var for Latest incoming massage
    private static String msg = "#: initialized successfully! \n" + "#: now running!";

    public int setMsg(String _msg) {
        // 0 -> sucsess
        // 1 -> this function is not available, another massage is already queued

        if (msg.isEmpty()) {
            msg = _msg;
            return 0;
        } else {
            return 1;
        }
    } // set new incoming msg

    public String getMsg() {
        return msg;
    }

    public void delMsg(String _msg) { // deleted msg after read verification
        if (_msg.equals(msg)) {
            msg = "";
        }
    }
}
