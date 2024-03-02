package org.pearharmony.control;

import org.pearharmony.control.commands.ClearHistory;
import org.pearharmony.control.commands.Contact;
import org.pearharmony.ui.TerminalMain;
import org.pearharmony.data.DataMain;

public class Control {

    TerminalMain ui;
    DataMain data;
    
    public Control() {
        data = new DataMain();
        initControl();
        ui = new TerminalMain(this);
        ui.initUI();
    }

    private void initControl(){
        System.out.println("Control INIT!");
    }

    public void executeCommand(String command) {
        String[] splitCommand = command.split("\\s+");
        switch (splitCommand[0]) {
            case "/contact" -> new Contact(ui, splitCommand);
            case "/clear-history" -> new ClearHistory(ui, splitCommand);
            default -> ui.displayError(1);
        }
    }

    public String getLatestMsgData() { return data.readAndDelTransMsg(); }
}
