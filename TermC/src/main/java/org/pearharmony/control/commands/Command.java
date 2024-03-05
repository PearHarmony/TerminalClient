// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public abstract class Command {

    // reference to UI
    protected TerminalMain ui;

    // command specific attributes
    protected String command;
    protected String syntax;

    // input string split every all spaces
    protected String[] subCommands;

    public Command(TerminalMain ui, String command, String syntax, String[] subCommands) {
        this.ui = ui;
        this.command = command;
        this.syntax = syntax;
        this.subCommands = subCommands;

        decodeCommand();
    }

    // function every child needs to decode the command string array
    protected abstract void decodeCommand();

    // syntax error massage derived from the syntax variable
    protected void errorSyntax() { ui.displayError("Wrong syntax! Use: " + syntax); }
}
