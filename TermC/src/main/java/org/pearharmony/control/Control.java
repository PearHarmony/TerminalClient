package org.pearharmony.control;

import org.pearharmony.ui.TerminalMain;
import org.pearharmony.data.DataMain;

public class Control {

    TerminalMain ui;
    DataMain data;
    
    public Control() {
        data = new DataMain();
        initControl();
        ui = new TerminalMain(this);
    }

    private void initControl(){
        System.out.println("Control INIT!");
    }

    public void executeCommand(String command) {
        System.out.println(command);
    }

    public String getLatestMsgData(){
        return data.readAndDelTransMsg();
    }
}
