package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public class Help extends Command {
    private final String commandSyntax = "/help";

    public Help(TerminalMain ui, String[] subCommand) {
        super(ui, "help", subCommand);
        setSyntax(commandSyntax);

        decodeCommand();
    }

    @Override
    protected void decodeCommand() {
        if (subCommands.length == 1 && subCommands[0].equals("/help")) {
            displayHelp();
        } else {
            errorSyntax();
        }
    }

    private void displayHelp() {
        ui.displayText("All commands: \n" +
                "-> /help - shows this dialog \n" +
                "-> /contact - add, edit or remove a contact from your address book \n" +
                "-> /clear-history - clear massage history");
    }
}
