package org.pearharmony.data;

public class DataMain {
    private MsgTrans msgTrans;
    public DataMain() {
        msgTrans = new MsgTrans();
    }

    public String readAndDelTransMsg() {
        String msg = msgTrans.getMsg();
        msgTrans.delMsg(msg);
        return msg;
    }
}
