package org.pearharmony.ui;

import com.googlecode.lanterna.gui2.TextBox;
import org.pearharmony.control.Control;

public class RunFunction {

    private Control ctrl;
    private TextBox msgBox;
    private TextBox msgHistory;

    public RunFunction(Control ctrl){
        this.ctrl = ctrl;
    }

    public void setupGUIFunction(TextBox msgBox, TextBox msgHistory) {
        this.msgBox = msgBox;
        this.msgHistory = msgHistory;
    }

    public void pressSendButton() {
        String msgText = msgBox.getText();

        if (msgText.matches("^/[^/]*$")) {
            ctrl.executeCommand(msgText);
        } else if (msgText.matches("^@[^@]*$")) {
            msgHistory.addLine("You: " + msgText);
        }

        msgBox.setText("");
    }

    public void timerTick(){
        String latestMsg = ctrl.getLatestMsgData();
        if(!latestMsg.isEmpty()) msgHistory.addLine(latestMsg);
    }
}
