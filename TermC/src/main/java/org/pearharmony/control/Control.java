// @C. Prickartz

package org.pearharmony.control;

import org.pearharmony.control.commands.*;
import org.pearharmony.data.DataMain;
import org.pearharmony.data.messages.TextMessage;
import org.pearharmony.network.NetworkControler;
import org.pearharmony.ui.TerminalMain;

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

    private void initControl() { // set up the control layer + display program info before UI Start
        System.out.println("""
                PearHarmony TerminalClient\s
                made by https://pearharmony.aocalux.dev\s
                GitHub: https://github.com/pearharmony\s
                PearHarmony TerminalClient ist licensed under the GNU LESSER GENERAL PUBLIC LICENSE Version 2.1.\s
                The client is developed in Java using the Java JDK 17, Maven and the Terminal GUI library Lanterna.\s
                Maven: https://maven.apache.org/ \s
                Lanterna: https://github.com/mabe02/lanterna""");

        NetworkControler netCtrl = new NetworkControler();
        netCtrl.startListening(10000);
    }

    public void executeCommand(String command) { // execute a command
        command = command.toLowerCase();
        String[] splitCommand = command.split("\\s+"); //split command at space ignore double spaces
        switch (splitCommand[0]) { // all possible commands
            case "/contact" -> new Contact(ui, splitCommand);
            case "/clear-history" -> new ClearHistory(ui, splitCommand);
            case "/help" -> new Help(ui, splitCommand);
            case "/info" -> new Info(ui, splitCommand);
            case "/send" -> new Send(ui, splitCommand);
            default -> ui.displayError(1); // command not found
        }
    }

    public void sendMessage(String msgText) {
        String[] msgSplit = msgText.split("\\s+");
        StringBuilder msgOnly = new StringBuilder();
        String address = msgSplit[0];
        msgSplit[0] = "";

        StringBuilder addressRemoveAt = new StringBuilder(address);
        addressRemoveAt.deleteCharAt(0);
        address = String.valueOf(addressRemoveAt);

        for (String msgTextOnly : msgSplit) {
            msgOnly.append(msgTextOnly).append(" ");
        }

        String msgToSend = String.valueOf(msgOnly);
        msgToSend = msgToSend.trim();

        TextMessage text = new TextMessage(address,msgToSend);
        text.send();
    }

    // read last massage dat from data layer
    public String getLatestMsgData() { return data.readAndDelTransMsg(); }
}
