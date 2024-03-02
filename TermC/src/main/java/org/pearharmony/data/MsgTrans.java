package org.pearharmony.data;

public class MsgTrans {
    private static String msg = "#: initialized successfully! \n" +
            "#: now running!";

    public void setMsg(String _msg) { msg = _msg; }

    public String getMsg() { return msg; }

    public int delMsg(String _msg) {
        if(_msg.matches(msg)) {
            msg = "";
            return 0;
        } else {
            return 1;
        }
    }
}
