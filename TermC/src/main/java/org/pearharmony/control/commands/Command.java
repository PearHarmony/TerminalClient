// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public abstract class Command {

    // reference to UI
    protected TerminalMain ui;

    // command specific attributes
    protected String command;
    private String syntax;

    // input string split every all spaces
    protected String[] subCommands;

    public Command(TerminalMain ui, String command, String[] subCommands){
        this.ui = ui;
        this.command = command;
        this.subCommands = subCommands;
    }

    // function every child needs to decode the command string array
    protected abstract void decodeCommand();

    protected void setSyntax(String syntax) { this.syntax = syntax; }

    // syntax error massage derived from the syntax variable
    protected void errorSyntax() {
        ui.displayError("Wrong syntax! Use: " + syntax);
    }
}
