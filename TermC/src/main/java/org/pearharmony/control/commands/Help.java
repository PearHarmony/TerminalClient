// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public class Help extends Command {
    private final String commandSyntax = "/help";

    public Help(TerminalMain ui, String[] subCommand) {
        super(ui, "help", subCommand);
        setSyntax(commandSyntax); // set general command syntax for syntax error

        decodeCommand();
    }

    @Override
    protected void decodeCommand() { // decode an execute subcommands
        if (subCommands.length == 1 && subCommands[0].equals("/help")) {
            displayHelp();
        } else {
            errorSyntax();
        }
    }

    private void displayHelp() { // display help menu
        ui.displayText("""

                -> All commands:\s
                -> /help - shows this dialog\s
                -> /contact - add, edit or remove a contact from your address book\s
                -> /clear-history - clear massage history\s
                -> /info - display program info\s
                ->\s
                -> Actions:\s
                -> ctrl + c - quit PearHarmony TerminalClient""");
    }
}
