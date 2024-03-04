// @C. Prickartz

package org.pearharmony.control;

import org.pearharmony.control.commands.ClearHistory;
import org.pearharmony.control.commands.Contact;
import org.pearharmony.control.commands.Help;
import org.pearharmony.control.commands.Info;
import org.pearharmony.ui.TerminalMain;
import org.pearharmony.data.DataMain;

import java.io.File;
import java.io.IOException;

public class Control {

    // references to data and ui layer
    TerminalMain ui;
    DataMain data;
    
    public Control() {
        data = new DataMain();
        initControl();
        ui = new TerminalMain(this);
        ui.initUI();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void initControl() { // set up the control layer + display program info before UI Start
        System.out.println("""
                PearHarmony TerminalClient\s
                made by https://pearharmony.aocalux.dev\s
                GitHub: https://github.com/pearharmony\s
                PearHarmony TerminalClient ist licensed under the GNU LESSER GENERAL PUBLIC LICENSE Version 2.1.\s
                The client is developed in Java using the Java JDK 17, Maven and the Terminal GUI library Lanterna.\s
                Maven: https://maven.apache.org/ \s
                Lanterna: https://github.com/mabe02/lanterna""");

        File contacts = new File("contacts");
        try { contacts.createNewFile(); }
        catch (IOException e) { throw new RuntimeException(e); }
    }

    public void executeCommand(String command) { // execute a command
        String[] splitCommand = command.split("\\s+"); //split command at space ignore double spaces
        switch (splitCommand[0]) { // all possible commands
            case "/contact" -> new Contact(ui, splitCommand);
            case "/clear-history" -> new ClearHistory(ui, splitCommand);
            case "/help" -> new Help(ui, splitCommand);
            case "/info" -> new Info(ui, splitCommand);
            default -> ui.displayError(1); // command not found
        }
    }

    // read last massage dat from data layer
    public String getLatestMsgData() { return data.readAndDelTransMsg(); }
}
