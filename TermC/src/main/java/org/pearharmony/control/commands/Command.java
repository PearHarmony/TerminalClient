package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public abstract class Command {
    protected TerminalMain ui;
    protected String command;
    private String syntax;

    protected String[] subCommands;

    public Command(TerminalMain ui, String command){
        this.ui = ui;
        this.command = command;
    }

    protected abstract void decodeCommand();

    protected void setSyntax(String syntax) { this.syntax = syntax; }

    protected void errorSyntax() {
        ui.displayError("Wrong syntax! Use: " + syntax);
    }
}
