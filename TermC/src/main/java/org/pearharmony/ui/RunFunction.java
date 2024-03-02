// @C. Prickartz

/*
    This class contains all ui functions trigger by an action event. In addition this class handles almost all output
    to the client massage history.
 */

package org.pearharmony.ui;

import com.googlecode.lanterna.gui2.TextBox;
import org.pearharmony.control.Control;

public class RunFunction {
    private Control ctrl; // reference to control

    // make editable UI elements global
    private TextBox msgBox;
    private TextBox msgHistory;

    public RunFunction(Control ctrl){
        this.ctrl = ctrl;
    }

    public void setupGUIFunction(TextBox msgBox, TextBox msgHistory) { // set up ui context after ui init
        this.msgBox = msgBox;
        this.msgHistory = msgHistory;
    }

    public void pressSendButton() { // send button press action
        String msgText = msgBox.getText();

        if (msgText.matches("^/[^/]*$")) { // match for slash at string start to identify command
            ctrl.executeCommand(msgText);
        } else if (msgText.matches("^@[^@]*$")) { // match for at at string start to identify outgoing msg
            msgHistory.addLine("You: " + msgText); // display own msg's
        }

        msgBox.setText(""); // clear massage box
    }

    public void timerTick(){ // regular timer function
        // check for new message and display if new
        String latestMsg = ctrl.getLatestMsgData();
        if(!latestMsg.isEmpty()) msgHistory.addLine(latestMsg);
    }

    // print system stuff to msg history
    public void printTo_msgHistory(String msg) { msgHistory.addLine("#: " + msg); }
}
