package org.pearharmony.control;

import org.pearharmony.control.commands.ClearHistory;
import org.pearharmony.control.commands.Contact;
import org.pearharmony.control.commands.Help;
import org.pearharmony.control.commands.Info;
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
        System.out.println("""
                PearHarmony TerminalClient\s
                made by https://pearharmony.aocalux.dev\s
                GitHub: https://github.com/pearharmony\s
                PearHarmony TerminalClient ist licensed under the GNU LESSER GENERAL PUBLIC LICENSE Version 2.1.\s
                The client is developed in Java using the Java JDK 17, Maven and the Terminal GUI library Lanterna.\s
                Java JDK: https://www.oracle.com/java/technologies/downloads/#java17 \s
                Maven: https://maven.apache.org/ \s
                Lanterna: https://github.com/mabe02/lanterna""");
    }

    public void executeCommand(String command) {
        String[] splitCommand = command.split("\\s+");
        switch (splitCommand[0]) {
            case "/contact" -> new Contact(ui, splitCommand);
            case "/clear-history" -> new ClearHistory(ui, splitCommand);
            case "/help" -> new Help(ui, splitCommand);
            case "/info" -> new Info(ui, splitCommand);
            default -> ui.displayError(1);
        }
    }

    public String getLatestMsgData() { return data.readAndDelTransMsg(); }
}
