package org.pearharmony.control;

import org.pearharmony.ui.TerminalMain;
import org.pearharmony.data.DataMain;

public class Control {

    TerminalMain ui;
    DataMain data;
    
    public Control() {
        ui = new TerminalMain(this);
        data = new DataMain();
        System.out.println("This is controling somthing!");
    }

    public void executeCommand(String command) {
        System.out.println(command);
    }
}
