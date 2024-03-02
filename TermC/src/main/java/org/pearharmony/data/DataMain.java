// @C. Prickartz

package org.pearharmony.data;

public class DataMain {
    private MsgTrans msgTrans;
    public DataMain() {
        msgTrans = new MsgTrans();
    }

    public String readAndDelTransMsg() { // read and delete latest massage
        String msg = msgTrans.getMsg();
        msgTrans.delMsg(msg);
        return msg;
    }
}
