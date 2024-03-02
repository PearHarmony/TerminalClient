// @C. Prickartz

package org.pearharmony.control.commands;

import org.pearharmony.ui.TerminalMain;

public class ClearHistory extends Command {

    private final String commandSyntax = "/clear-history";

    public ClearHistory(TerminalMain ui, String[] subCommands) {
        super(ui, "clear-history", subCommands);
        setSyntax(commandSyntax); // set general command syntax for syntax error

        decodeCommand();
    }

    @Override
    protected void decodeCommand() { // decode an execute subcommands
        if (subCommands.length == 1 && subCommands[0].equals("/clear-history")) {
            ui.clearHistory();
        } else {
            errorSyntax();
        }
    }
}
